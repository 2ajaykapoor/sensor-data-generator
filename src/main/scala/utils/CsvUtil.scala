package utils

import monix.eval.Task
import monix.reactive.Observable

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Random
import monix.execution.Scheduler.Implicits.global

object CsvUtil {

  def generateCsvWithData(fileName: String, numEntries: Int, numSensors: Int): Unit = {

    val sensorData: List[(String, Int)] = (1 to numEntries).map { _ =>
      val sensorId = s"sensor-${Random.nextInt(numSensors) + 1}"
      val humidity = Random.nextInt(103) - 1 // Range: -1 to 101
      (sensorId, humidity)
    }.toList

    // Create an Observable from the sensor data
    val observable: Observable[String] =
      Observable.fromIterable(sensorData)
        .map { case (sensorId, humidity) => s"$sensorId,$humidity" }
    // Add the header
    val headerObservable: Observable[String] = Observable.pure("sensor-id,humidity") ++ observable

    // Define the output file path
    val outputPath = Paths.get(s"$fileName.csv")

    // Write the data to the CSV file
    val writeTask: Task[Unit] =
      headerObservable
        .toListL
        .flatMap(lines => Task {
          Files.write(outputPath, lines.mkString("\n").getBytes(StandardCharsets.UTF_8))
        })

    Await.result(writeTask.runToFuture, Duration.Inf)
    println(s"CSV data written to ${outputPath.toAbsolutePath}")
  }
}