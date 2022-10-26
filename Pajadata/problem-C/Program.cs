using System;
using System.Text;
using System.Collections.Generic;

namespace pajadata
{
    class ProblemC
    {
        static int CountPixels(int[] arr)
        {
            int sum = 0;
            for (int i = 0; i < arr.Length; i++)
            {
                sum += arr[i];
            }
            return sum;
        }

        static string PrintRun(char ch, int runLength)
        {
            string result = "";

            for (int i = 0; i < runLength; i++)
            {
                result += ch;
            }

            return result;
        }

        static string PrintLine(char firstChar, int[] pixelRuns)
        {
            string result = "";
            char printedChar = firstChar;

            for (int i = 0; i < pixelRuns.Length; i++)
            {
                result += PrintRun(printedChar, pixelRuns[i]);
                if (printedChar == '#') { printedChar = '.'; }
                else { printedChar = '#'; }
            }

            result += "\n";
            return result;
        }
        public static void Main(string[] args)
        {
            StringBuilder results = new StringBuilder();
            var line = Console.ReadLine();

            // check whether input is 0 indicating the end of program
            while (!string.IsNullOrEmpty(line) && int.Parse(line) != 0)
            {

                // begin to decode each image
                Boolean error = false;
                int scanlinePixelNo = 0;
                StringBuilder imageResult = new StringBuilder();

                line = Console.ReadLine();

                while (!string.IsNullOrEmpty(line) && !int.TryParse(line, out int number))
                {

                    string[] scanline = line.Split(' ');
                    char firstChar = char.Parse(scanline[0]);

                    int[] runPixels = new int[scanline.Length - 1];
                    for (int i = 0; i < runPixels.Length; i++)
                    {
                        runPixels[i] = int.Parse(scanline[i + 1]);
                    }

                    imageResult.Append(PrintLine(firstChar, runPixels));

                    if (scanlinePixelNo != 0 && scanlinePixelNo != CountPixels(runPixels))
                    {
                        error = true;
                    }
                    scanlinePixelNo = CountPixels(runPixels);

                    line = Console.ReadLine();
                }

                results.Append(imageResult.ToString());
                if (error)
                {
                    results.Append("Error decoding image\n");
                }

                results.Append("\n");

            }

            Console.Write(results.ToString());
        }
    }
}