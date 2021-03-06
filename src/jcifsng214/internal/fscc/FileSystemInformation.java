/*
 * © 2017 AgNO3 Gmbh & Co. KG
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package jcifsng214.internal.fscc;


import jcifsng214.Decodable;


/**
 * @author mbechler
 *
 */
public interface FileSystemInformation extends Decodable {

    /**
     * 
     */
    public static final byte SMB_INFO_ALLOCATION = -1;

    /**
     * 
     */
    public static final byte FS_SIZE_INFO = 3;
    /**
     * 
     */
    public static final byte FS_FULL_SIZE_INFO = 7;


    /**
     * 
     * @return file system information class
     */
    byte getFileSystemInformationClass ();
}
