/* jcifs smb client library in Java
 * Copyright (C) 2003  "Michael B. Allen" <jcifs at samba dot org>
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

package jcifsng214.smb;


import jcifsng214.DfsReferralData;


/**
 * 
 * @author mbechler
 *
 * @internal
 */
@SuppressWarnings ( "javadoc" )
public class DfsReferral extends SmbException {

    /**
     * 
     */
    private static final long serialVersionUID = 1486630733410281686L;

    private final DfsReferralData data;


    /**
     * @param dr
     */
    public DfsReferral ( DfsReferralData data ) {
        this.data = data;
    }


    public DfsReferralData getData () {
        return this.data;
    }


    @Override
    public String toString () {
        return this.data.toString();
    }
}
