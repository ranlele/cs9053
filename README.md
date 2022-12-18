# CS 9053 Project

Purpose of Project: We want to exercise our understanding of a few of java concepts by implementing different poker games, and recording players' points in a database.


## Team 
**Shuo Chen(sc8805),  Jingyu Wang(jw7302),  Jingyan Ran(jr5816)**

## Features

1. User Interface Design

2. A BlackJack game with computer opponent. A multi-threaded Bridge game. Every player is a single thread.

3. A DB where results could be stored and retrieved.

## Description

1.  Blackjack is a casino banking game. The most widely played casino banking game in the world, it uses decks of 52 cards. The game is a comparing card game where each player competes against the dealer. In this game, there will be four parts, betting, shuffling and cutting, dealing, and the play. For the first part, we will use multi-thread to deal with each players process of betting, in chips. If everyone has made their decisions, the system will shuffle and cut the cards automatically. Then in the dealing process, the dealer(system) gives one card face up to each player in rotation clockwise. After dealing, The player to the left goes first and must decide whether to "stand" (not ask for another card) or "hit" (ask for another card in an attempt to get closer to a count of 21, or even hit 21 exactly) until the end.

  

2.  The bridge is a [trick-taking](https://en.wikipedia.org/wiki/Trick-taking_game)  [card game](https://en.wikipedia.org/wiki/Card_game) using a [standard 52-card deck](https://en.wikipedia.org/wiki/Standard_52-card_deck). In this assignment, we want to implement a contract bridge game that has 4 players who are divided into 2 competing partnerships. The game has two parts. In the first part, players bid in turn. The partnership that comes up with the highest contract is the winning bidder and must get at least as many tricks as the contract required to score points. The second part is playing cards. Players play their cards in clockwise order. In every trick, all the cards which are played should have the same suit unless the player does not have a card of this suit anymore. The player who played the highest card in the trick wins this trick unless another player has a trump card. The points each player can get are relevant to the tricks its partnership wins and the tricks the contract requires. We will use GUIs to implement this game. The score of the user will be sent to the network server and saved in a database.

3.  A Merit Growth Machine. In Buddhism, people gain merit by knocking the wooden fish ([Muyu](https://www.youtube.com/watch?v=b6gKkhX63-o)). In order to keep people from being addicted to gambling, they need to knock the wooden fish to gain merit and keep points. Otherwise, the points will not be stored in the database.

## **How to play**

Please download the zip file and make sure the SQLite jar file is included. Then go to the cardGame/src/common. running mainPage.java to start the game. After entering your name, you can play blackjack and bridge game. Finally you can save your balance by knocking the Muyu. You will find your rank on the leaderboard on Muyu Page.

