/*
 * This file is part of KITCard Reader.
 * Ⓒ 2012 Philipp Kern <phil@philkern.de>
 *
 * KITCard Reader is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * KITCard Reader is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with KITCard Reader. If not, see <http://www.gnu.org/licenses/>.
 */

package eu.tobias_schulz.kitcard_reader;

public class MifareUtils {

	static int toUInt16BE(byte b1, byte b2) {
		return (MifareUtils.byteToInt(b1) << 8) + MifareUtils.byteToInt(b2);
	}

	static int byteToInt(byte value) {
		return (value < 0) ? value + 256 : value;
	}

	static byte intToByte(int value) {
		return (byte)((value > 128) ? value - 256 : value);
	}

	static int toUInt16LE(byte b1, byte b2) {
		return toUInt16BE(b2, b1);
	}

}
