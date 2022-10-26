using System;
using System.Text;
using System.Collections.Generic;

namespace pajadata
{
    class ProblemA
    {
        static void Main(string[] args)
        {
            List<string> results = new List<string>();
            int?[] bits = new int?[32];
            var line = Console.ReadLine();

            while (!string.IsNullOrEmpty(line) && int.Parse(line) != 0)
            {
                line = Console.ReadLine();

                while (!string.IsNullOrEmpty(line) && !int.TryParse(line, out int number))
                {
                    string[] command = line.Split(' ');

                    switch (command[0])
                    {
                        case "CLEAR":

                            bits[int.Parse(command[1])] = 0;
                            break;
                        case "SET":

                            bits[int.Parse(command[1])] = 1;
                            break;
                        case "OR":

                            if (
                                bits[int.Parse(command[1])] != null
                                && bits[int.Parse(command[2])] != null
                            )
                            {
                                bits[int.Parse(command[1])] =
                                    bits[int.Parse(command[1])] | bits[int.Parse(command[2])];
                            }
                            else if (
                                bits[int.Parse(command[1])] == 1 || bits[int.Parse(command[2])] == 1
                            )
                            {
                                bits[int.Parse(command[1])] = 1;
                            }
                            else
                            {
                                bits[int.Parse(command[1])] = null;
                            }
                            break;
                        case "AND":

                            if (
                                bits[int.Parse(command[1])] != null
                                && bits[int.Parse(command[2])] != null
                            )
                            {
                                bits[int.Parse(command[1])] =
                                    bits[int.Parse(command[1])] & bits[int.Parse(command[2])];
                            }
                            else if (
                                bits[int.Parse(command[1])] == 0 || bits[int.Parse(command[2])] == 0
                            )
                            {
                                bits[int.Parse(command[1])] = 0;
                            }
                            else
                            {
                                bits[int.Parse(command[1])] = null;
                            }
                            break;
                        default:
                            Console.WriteLine("Invalid command");
                            break;
                    }

                    line = Console.ReadLine();
                }

                StringBuilder result = new StringBuilder();

                for (int i = 31; i >= 0; i--)
                {
                    if (bits[i] != null)
                    {
                        result.Append(bits[i]);
                    }
                    else
                    {
                        result.Append('?');
                    }
                }

                results.Add(result.ToString());

                bits = new int?[32];
            }

            Console.WriteLine(string.Join("\n", results));
        }
    }
}
