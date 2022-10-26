using System;
using System.Text;
using System.Collections.Generic;

namespace pajadata
{
    class ProblemB
    {

        static Boolean CheckBugPosition(int X, int Y, char[,] map)
        {
            return (X < 0 || X > 7 || Y < 0 || Y > 7 || map[Y, X] == 'C' || map[Y, X] == 'I');
        }

        static Boolean CheckDiamondPosition(int X, int Y, char[,] map)
        {
            return (X >= 0 && X <= 7 && Y >= 0 && Y <= 7 && map[Y, X] == 'D');
        }

        static void Main(string[] args)
        {
            // input the map
            char[,] map = new char[8, 8];
            for (int i = 0; i < 8; i++)
            {
                string line = Console.ReadLine();

                if (!string.IsNullOrEmpty(line))
                {
                    for (int j = 0; j < 8; j++)
                    {
                        map[i, j] = line[j];
                    }
                }
            }

            // turtle's data (positions, direction, result)
            int Xpos, Ypos;
            char direction;
            string result = "Bug!";

            // getting instructions from user input
            char[] instructions;
            string command = Console.ReadLine();

            if (!string.IsNullOrEmpty(command))
            {
                Xpos = 0;
                Ypos = 7;
                direction = 'R';
                instructions = command.ToCharArray();


                bool bug = false;
                bool diamond = false;

                if (instructions.Length <= 60)
                {
                    for (int i = 0; i < instructions.Length; i++)
                    {

                        switch (direction)
                        {
                            // when the turtle faces left
                            case 'L':
                                switch (instructions[i])
                                {
                                    // moving left
                                    case 'F':
                                        Xpos--;
                                        break;
                                    // turning to the right (left to up)
                                    case 'R':
                                        direction = 'U';
                                        break;
                                    // turning to the left (left to down)
                                    case 'L':
                                        direction = 'D';
                                        break;
                                    // shooting laser to the left
                                    case 'X':
                                        if (Xpos - 1 >= 0 && map[Ypos, Xpos - 1] == 'I')
                                        {
                                            map[Ypos, Xpos - 1] = '.';
                                        }
                                        else
                                        {
                                            bug = true;
                                        }
                                        break;
                                    default:
                                        break;
                                }

                                break;

                            // when the turtle faces right
                            case 'R':
                                switch (instructions[i])
                                {
                                    // moving right
                                    case 'F':
                                        Xpos++;
                                        break;
                                    // turning to the right (right to down)
                                    case 'R':
                                        direction = 'D';
                                        break;
                                    // turning to the left (right to up)
                                    case 'L':
                                        direction = 'U';
                                        break;
                                    // shooting laser to the right
                                    case 'X':
                                        if (Xpos + 1 <= 7 && map[Ypos, Xpos + 1] == 'I')
                                        {
                                            map[Ypos, Xpos + 1] = '.';
                                        }
                                        else
                                        {
                                            bug = true;
                                        }
                                        break;
                                    default:
                                        break;
                                }

                                break;

                            // when the turtle faces up    
                            case 'U':
                                switch (instructions[i])
                                {
                                    // moving up
                                    case 'F':
                                        Ypos--;
                                        break;
                                    // turning to the right (up to right)
                                    case 'R':
                                        direction = 'R';
                                        break;
                                    // turning to the left (up to left)
                                    case 'L':
                                        direction = 'L';
                                        break;
                                    // shooting laser upwards
                                    case 'X':
                                        if (Ypos - 1 >= 0 && map[Ypos - 1, Xpos] == 'I')
                                        {
                                            map[Ypos - 1, Xpos] = '.';
                                        }
                                        else
                                        {
                                            bug = true;
                                        }
                                        break;
                                    default:
                                        break;
                                }

                                break;

                            // when the turtle faces down    
                            case 'D':
                                switch (instructions[i])
                                {
                                    // moving down
                                    case 'F':
                                        Ypos++;
                                        break;
                                    // turning to the right (down to left)
                                    case 'R':
                                        direction = 'L';
                                        break;
                                    // turning to the left (down to right)
                                    case 'L':
                                        direction = 'R';
                                        break;
                                    // shooting laser downwards
                                    case 'X':
                                        if (Ypos + 1 <= 7 && map[Ypos + 1, Xpos] == 'I')
                                        {
                                            map[Ypos + 1, Xpos] = '.';
                                        }
                                        else
                                        {
                                            bug = true;
                                        }
                                        break;
                                    default:
                                        break;
                                }

                                break;
                            default:
                                break;
                        }

                        bug = CheckBugPosition(Xpos, Ypos, map);
                        diamond = CheckDiamondPosition(Xpos, Ypos, map);

                        if (bug)
                        {
                            break;
                        }
                        if (diamond)
                        {
                            result = "Diamond!";
                            break;
                        }

                    }
                }

            }
            Console.WriteLine(result);
        }
    }
}