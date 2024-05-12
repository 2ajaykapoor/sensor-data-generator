import utils.CsvUtil

import scala.io.StdIn.readLine
object Start extends App {
  println("Enter No of files to be generated.")
  val noOfFiles = readLine()
  println("Enter No of Sensors to be included.")
  val noOfSensors = readLine()
  println("Enter No of records to be generated in individual file.")
  val noOfRecords = readLine()

  (1 to noOfFiles.toInt).foreach(fileNo => {
    CsvUtil.generateCsvWithData(s"leader-$fileNo", noOfRecords.toInt, noOfSensors.toInt)
  })
}
