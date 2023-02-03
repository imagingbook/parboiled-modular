/*
 * Copyright (C) 2009-2011 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.parboiled.support;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CharactersTest {

    @Test
    public void testSimpleCharactersOps() {
        assertEquals(Characters.NONE.toString(), "[]");
        assertEquals(Characters.ALL.toString(), "![]");

        assertEquals(Characters.NONE.add('A').toString(), "[A]");
        assertEquals(Characters.ALL.add('A'), Characters.ALL);

        assertEquals(Characters.NONE.remove('A'), Characters.NONE);
        assertEquals(Characters.ALL.remove('A').toString(), "![A]");
        assertEquals(Characters.of('A').remove('A'), Characters.NONE);
    }

    @Test
    public void testMultiCharactersOps() {
        assertEquals(Characters.NONE.add(Characters.ALL), Characters.ALL);
        assertEquals(Characters.ALL.add(Characters.NONE), Characters.ALL);

        assertEquals(Characters.NONE.remove(Characters.ALL), Characters.NONE);
        assertEquals(Characters.ALL.remove(Characters.NONE), Characters.ALL);

        assertEquals(Characters.ALL.remove(Characters.of('A', 'B')).toString(), "![AB]");
        assertEquals(Characters.ALL.remove(Characters.allBut('A', 'B')).toString(), "[AB]");

        assertEquals(Characters.of('A', 'B').add(Characters.of('B', 'C')), Characters.of('A', 'B', 'C'));
        assertEquals(Characters.allBut('A', 'B').add(Characters.of('B', 'C')), Characters.allBut('A'));
        assertEquals(Characters.of('A', 'B').add(Characters.allBut('B', 'C')), Characters.allBut('C'));
        assertEquals(Characters.allBut('A', 'B').add(Characters.allBut('B', 'C')), Characters.allBut('B'));

        assertEquals(Characters.of('A', 'B').remove(Characters.of('B', 'C')), Characters.of('A'));
        assertEquals(Characters.allBut('A', 'B').remove(Characters.of('B', 'C')), Characters.allBut('A', 'B', 'C'));
        assertEquals(Characters.of('A', 'B').remove(Characters.allBut('B', 'C')), Characters.of('B'));
        assertEquals(Characters.allBut('A', 'B').remove(Characters.allBut('B', 'C')), Characters.of('C'));
    }

    @Test   // wilbur: added
    public void testContains() {
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Characters LETTERS = Characters.of(letters);
        Characters NONLETTERS = Characters.allBut(letters);

        assertTrue(LETTERS.contains('z'));
        assertFalse(LETTERS.contains('0'));

        assertTrue(NONLETTERS.contains('1'));
        assertFalse(NONLETTERS.contains('a'));

        for (char c : letters.toCharArray()) {
            assertTrue(LETTERS.contains(c));
            assertFalse(NONLETTERS.contains(c));
        }
    }

    @Test   // wilbur: added
    public void testContainsTiming() {
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Characters LETTERS = Characters.of(letters);
        long startTime = System.nanoTime();
        int cnt = 0;
        for (int i = 0; i < 1000000; i++) {
            if (LETTERS.contains((char) i)) {
                cnt++;
            }
        }
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Elapsed time: " + (elapsedTime / 1000L) + "ms");
        System.out.println(cnt);
    }

}
