package temp
import collection.JavaConverters.*
import cats.data.State
import cats.effect.IO

import scala.util.Random
import cats.syntax.flatMap
import org.apache.commons.io.IOUtils

import java.io.{FileInputStream, FileOutputStream}
import java.util.UUID
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

import java.util
import scala.collection.mutable.ListBuffer
object Cfg {
    val totalSec = 10000
    val totalUser = 100
    val secRange = (500, 1000)
    val componetRange = (3, 8)


//    val totalSec = 2
//    val totalUser = 2
//    val secRange = (1, 2)
//    val componetRange = (1, 2)
}
@main
def main(): Unit = {
    val usages = generateUsage(getUser().take(Cfg.totalUser), randomList(getSecurity(), Cfg.totalSec))
    val userIds = usages._3.map(e => e._1).toSet.toList.sorted
    val secIds = usages._3.map(e => e._2).toSet.toList.sorted
    val componetIds = AvComponent.all().map(e => e.toId()).sorted

    val userIdsMap = toInex(userIds)
    val secIdsMap = toInex(secIds)
    val cIdsMap = toInex(componetIds)

    IOUtils.write(usages._1.map(e => s"${e._1},${e._2},${e._3}").mkString("\n"), FileOutputStream("C:/data/DNALab/ai-data/security-data.csv"))
    IOUtils.write(usages._2.map(e => s"${e._1},${e._2},${e._3}").mkString("\n"), FileOutputStream("C:/data/DNALab/ai-data/componnet-data.csv"))
    IOUtils.write(usages._3.map(e => s"${e._1},${e._2},${e._3},${e._4}").mkString("\n"), FileOutputStream("C:/data/DNALab/ai-data/all.csv"))

    IOUtils.write(usages._1.map(e => s"${userIdsMap.get(e._1)},${secIdsMap.get(e._2)},${e._3}").mkString("\n"), FileOutputStream("C:/data/DNALab/ai-data/security-data-2.csv"))
    IOUtils.write(usages._2.map(e => s"${secIdsMap.get(e._1)},${cIdsMap.get(e._2)},${e._3}").mkString("\n"), FileOutputStream("C:/data/DNALab/ai-data/componnet-data-2.csv"))
   // IOUtils.write(usages._3.map(e => s"${e._1},${e._2},${e._3},${e._4}").mkString("\n"), FileOutputStream("C:/data/DNALab/ai-data/all.csv"))
    IOUtils.write(mapToString(userIdsMap), FileOutputStream("C:/data/DNALab/ai-data/userIdMapping.txt"))
    IOUtils.write(mapToString(secIdsMap), FileOutputStream("C:/data/DNALab/ai-data/secIdMapping.txt"))
    IOUtils.write(mapToString(cIdsMap), FileOutputStream("C:/data/DNALab/ai-data/componetIdMapping.txt"))
    println("complete")

}
def mapToString(map: util.HashMap[String, Int]): String ={
    val m = map.asScala
    m.toList.sortBy(e => e._2).map(e => s"${e._2} => ${e._1}").mkString("\n")
}
def toInex(data: List[String]): util.HashMap[String, Int] = {
    val map = new util.HashMap[String, Int]()
    data.zipWithIndex.foreach{e =>
        map.put(e._1, e._2 + 1)
    }
    map
}



def generateUsage(userIds: List[String], secIds: List[String])
:(List[((String, String, Int))], List[((String, String, Int))], List[((String, String, String, Int))]) ={
    val componentUsages: List[(String, String, String, Int)] = for {
        userId <- userIds
        secId <- randomList(secIds, random(Cfg.secRange._1, Cfg.secRange._2))
        componentId <- randomList(AvComponent.all().map(_.toId()), random(Cfg.componetRange._1, Cfg.componetRange._2 ))
    } yield (userId, secId, componentId, random(1, 10))

    val list = componentUsages.filter(e => e._4 == 1)
    (summaryData(true, componentUsages), summaryData(false, componentUsages), componentUsages)
}

def toRating(usage: List[(String, String, Int)]): Unit = {
    val us2 = usage.map(_._3)
    val min = us2.min
    val max = us2.max
}


def summaryData(isSec:Boolean, usages:List[(String, String, String, Int)]): List[(String, String, Int)] = {
    val l:Map[String, Int] = if(isSec) {
        usages.groupBy(e => s"${e._1}@${e._2}").map(kv => (kv._1, kv._2.map(_._4).sum))
    } else {
        usages.groupBy(e => s"${e._2}@${e._3}").map(kv => (kv._1, kv._2.map(_._4).sum))
    }
    val result = l.toList.map(e => {
        val arr = e._1.split("@")
        (arr(0), arr(1), e._2)
    })
    result
}

case class IdName(id:String, name:String)
case class Securities(total:Int, investments:List[IdName])

def getSecurity():List[String] = {
    val json = IOUtils.toString(FileInputStream("C:\\data\\DNALab\\security.txt"))
    val decoded:Securities = decode[Securities](json).toOption.get
    decoded.investments.map(_.id)
}
def getUser():List[String] = {
    IOUtils.readLines(FileInputStream("C:\\data\\DNALab\\user.txt"))
      .asScala.map(_.trim).toList.map(_.toUpperCase())
}
def randomId():String = UUID.randomUUID().toString()

def trackingToRating(trackingNum:Int, max:Int) = {
    val a = trackingNum.toDouble / max
    val rating = Math.round(a*5).toInt
    if(rating == 0) rating + 1 else rating
}

def randomE[T](list: List[T]): T = list(random(0, list.length - 1))
def randomList[T](list: List[T], n:Int): List[T] = {
    val buffer = list.toBuffer
    var curr = 0
    var result = List.empty[T].toBuffer
    while(curr < n) {
        val i = random(0, buffer.size - 1)
        val t = buffer.remove(i)
        result.append(t)
        curr += 1
    }
    result.toList
}
def random(min:Int, max:Int):Int = {
    val rand = new Random()
    rand.nextInt((max - min) + 1) + min
}
def randomExclude(from: Int, to:Int, exclude:Int, retry:Int = 100):Int = {
    val result = random(from, to)
    if(result != exclude || retry <=0) {
        result
    } else {
        randomExclude(from, to, exclude, retry - 1)
    }
}
def randomStream(from:Int, to:Int):Stream[Int] = random(from, to) #:: randomStream(from, to)
def randomN(from:Int, to:Int, n:Int) = randomStream(from, to).take(n).toList

def randomWithWeight(weight: Double, from:Int, to:Int, target:Int):Int = {
    val result = random(from, to)
    val r = random(0, 100)
    ifElse(r <= weight * 100, target, randomExclude(from, to, target))
}

def ifElse[A, B](condition: Boolean, ifRes: => A, elseRes: => B): A|B = {
    if(condition) ifRes else elseRes
}

enum AvComponent {
    case av_components_correlation
    case av_components_growth
    case av_components_risk_reward
    case av_components_holdings_similarity
    case av_components_price_chart
    case av_components_performance_peer_group
    case av_components_rolling_return
    case av_components_esg_pillar_scores
    case av_components_performance_analysis
    case av_components_esg_involvement

    def toId():String = this.toString.replace("_", "-")
}
object AvComponent {
    def random(): AvComponent ={
        randomE(AvComponent.values.toList)
    }
    def all():List[AvComponent] = AvComponent.values.toList
}