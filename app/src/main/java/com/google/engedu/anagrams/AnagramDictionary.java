/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private HashSet<String> wordSet;
    private HashMap<String, ArrayList<String>> lettersToWord;


    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        wordSet = new HashSet<>();
        lettersToWord = new HashMap<>();
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordSet.add(word);
            String sortedWord = sortLetters(word);
            if(lettersToWord.containsKey(sortedWord)) {
                ArrayList<String> value = lettersToWord.get(sortedWord);
                value.add(word);
                lettersToWord.put(sortedWord, value);
            }
            else {
                ArrayList<String> value = new ArrayList<>();
                value.add(word);
                lettersToWord.put(sortedWord, value);
            }
        }

    }

    public boolean isGoodWord(String word, String base) {

        return wordSet.contains(word) && (word.indexOf(base) < 0);
    }

//    public List<String> getAnagrams(String targetWord) {
//        ArrayList<String> result = new ArrayList<String>();
//        String sortedTargetWord = sortLetters(targetWord);
//        if(wordSet.contains(targetWord)) {
//            if(lettersToWord.containsKey(sortedTargetWord)) {
//                lettersToWord.
//                result.add(word);
//            }
//        }
//        return result;
//    }

    private String sortLetters(String word) {
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        return Arrays.toString(letters);
    }

    public List<String> getAnagramsWithOneMoreLetter(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        for(int i=97; i<123; i++) {
            String modifiedTargetWord = targetWord + Character.toString ((char) i).toString();
            String sortedModifiedTargetWord = sortLetters(modifiedTargetWord);
            if(lettersToWord.containsKey(sortedModifiedTargetWord)) {
                result.addAll(lettersToWord.get(sortedModifiedTargetWord));
            }
        }
        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }
}
