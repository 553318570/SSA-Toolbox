/*

Copyright (c) 2010, Jan Saputra Müller, Paul von Bünau, Frank C. Meinecke,
Franz J. Kiraly and Klaus-Robert Müller.
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or other
 materials provided with the distribution.

* Neither the name of the Berlin Institute of Technology (Technische Universität
Berlin) nor the names of its contributors may be used to endorse or promote
products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 */

package ssatoolbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for storing the toolbox configuration in a file.
 *
 * @author Jan Saputra Mueller, saputra@cs.tu-berlin.de
 */
public class ToolboxConfig
{
    private final String CONFIG_DIR = ".ssa-toolbox";
    private final String CONFIG_FILE = "config";

    private File configFile;
    private Properties properties;

    /**
     * ToolboxConfig constructor. It loads the current configuration of the toolbox from
     * the config-file.
     */
    public ToolboxConfig()
    {
        // get user home directory
        String userHome = System.getProperty("user.home");
        // get file separator
        String fileSep = System.getProperty("file.separator");
        configFile = new File(userHome + fileSep + CONFIG_DIR + fileSep + CONFIG_FILE);
        properties = new Properties();
        if(configFile.exists())
        {
            try
            {
                // load properties
                FileInputStream fis = new FileInputStream(configFile);
                properties.load(fis);
                fis.close();
            }
            catch (IOException ex) { }
        }
        else
        {
            // does config directory exist?
            File configDir = new File(userHome + fileSep + CONFIG_DIR);
            if(!configDir.exists())
            {
                // make directory
                configDir.mkdir();
            }
        }
    }

    /**
     * Sets a property.
     *
     * @param Key property name
     * @param Value property value
     */
    public void setProperty(String Key, String Value)
    {
        properties.setProperty(Key, Value);
    }

   /**
     * Gets a property.
     *
     * @param Key property name
     */
    public String getProperty(String Key)
    {
        return properties.getProperty(Key);
    }

    /**
     * Stores the current configuration in the config-file.
     */
    public void saveProperties()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(configFile);
            properties.store(fos, null);
        } catch (IOException ex) { }
    }
}
