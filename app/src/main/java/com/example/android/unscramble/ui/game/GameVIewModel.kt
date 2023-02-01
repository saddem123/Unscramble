package com.example.android.unscramble.ui.game

import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private var _score = 0
    private var _currentWordCount = 0
    private lateinit var _currentScrambledWord: String
    private var _count = 0
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    val count: Int
       get() = _count

    val score: Int
        get() = _score

    val currentWordCount: Int
        get() = _currentWordCount

    val currentScrambledWord: String
        get() = _currentScrambledWord


    init {
        getNextWord()
    }

    private fun getNextWord(){
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()
        while(String(tempWord).equals(currentWord, false)){
            tempWord.shuffle()
        }
        if(wordsList.contains(currentWord)){
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)
        }
    }

    fun nextWord(): Boolean {
        return  if(currentWordCount < MAX_NO_OF_WORDS){
            getNextWord()
            true
        }else false
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if(playerWord.equals(currentWord,true)){
            increaseScore();
            return true;
        }
        return false
    }

    /*
* Re-initializes the game data to restart the game.
*/
    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordsList.clear()
        getNextWord()
    }
}