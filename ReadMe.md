# Ultimate Tic Tac Toe

The project is an Ultimate Tic Tac Toe Game, that includes a PVC mode.
The algorithm of the Bot is based on the Monte Carlo Tree Search algorithm.

## Usage
The Project can be started by importing the project and starting the main class.

## Gameplay
The game is played on a board with 9x9 squares, which is divided into nine smaller local boards of 3x3 squares. 
The players, X and O, take turns moving, X goes first. The player whose turn it is, enters his symbol in a field 
that is still free (places in this field).

The starting player can enter his first symbol in any field. Thereafter, each move must be placed in the local board 
given by the position of the previous move in its local board. If e.g. For example, if a player is placed in the upper 
right field of a local board, the next move must be made in the upper right local board. The moving player can therefore 
only choose which free space on the given local board he places in.

If a player forms a row of three of his symbols (horizontally, vertically or diagonally) on a local board, he has won this 
local board and no more bets can be placed on it. If the local board given by the previous move has already been won by a 
player or is fully occupied, the subsequent move can be placed in any other local board that has not yet been won.

The aim of the game is to win three local boards, which in turn form a horizontal, vertical or diagonal row on the 
global board. If no more moves are possible and no player has met the victory condition, the game ends in a draw