package statistics

/**
 * 算术平均数
 * @param datas
 * @return
 */
def arithmeticMean(datas: Array[Int]): Int = datas.sum / datas.length

/**
 * 几何平均数
 * 几何平均数是对各变量值的连乘积开n项数次方根
 * @param datas
 * @return
 */
def geometricMean(datas: Array[Int]): Double = Math.pow(datas.product, 1.toDouble / datas.length)

/**
 * 标准差，均方差
 * @param datas
 * @return
 */
def standardDeviation(datas: Array[Int]): Double = {
    val avg:Double = datas.sum / datas.length.toDouble
    val r = datas.map(e => (e - avg) * (e - avg)).sum / datas.length
    Math.pow(r, 1/2)
}

@main
def main(): Unit ={
    println(geometricMean(Array(3,3)))
}