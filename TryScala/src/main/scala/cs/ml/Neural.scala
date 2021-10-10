package cs.ml
import cs.extension.*

type Sigmoid = Double | Float | Int | Long | Short | Byte => Double // 激活函数
type Output = Double
type Matric = Array[Array[Double]]

case class Input(input: Double, weight: Double)

/**
 * 获取神经网络某个输出节点的值<br>
 * output = input * sigmoid<br>
 * @param inputs 针对该输出节点的所有输入，每个输入新鲜包含原始输入和对应的权重
 * @param sigmoid 激活函数
 */
def output(inputs:Array[Input], sigmoid: Sigmoid): Output = {
  inputs.map(i => i.input * i.weight).sum |> sigmoid
}

/**
 * 3层神经网络
 * @param inputNodeNum 输入节点数目
 * @param hiddenNodeNum 隐藏层节点数目
 * @param outputNodeNum 输出层节点数目
 * @param learningRate 学习率
 */
class NeuralNetwork(inputNodeNum: Int, hiddenNodeNum: Int, outputNodeNum: Int, learningRate:Double){
  val weightInputHidden: Array[Array[Double]] = generateWeight(inputNodeNum, hiddenNodeNum) // input -> hidden 的权重矩阵
  val weightHiddenOuput: Array[Array[Double]] = generateWeight(hiddenNodeNum, outputNodeNum) // hidden -> output 的权重矩阵

  /**
   * 训练神经网络
   */
  def train(): Unit ={

  }

  /**
   * 查询神经网络
   * @param inputList 输入节点的值，比如有n个输入，值inpusts.len = n
   */
  def query(inputList: Array[Double]): Unit ={
      val inputs:Array[Array[Double]] = numpy.arrayT(inputList, 2) // 将inputList转换为 2维矩阵
  }

  /**
   * 激活函数 sigmoid
   */
  private def activeFunction[T](t: T): T = ???
}

object numpy {
  def dot(): Unit ={

  }

  def arrayT[T](m: Array[T], n: Int):Array[Array[T]] = ???
}


/**
 * 随机创建权重矩阵
 * @param m
 * @param n
 * @return
 */
def generateWeight(m: Int, n: Int):Array[Array[Double]] = ???

/**
 * 识别手写数字
 * 输入: 一张图片，将像素转为二维数组
 * 隐藏层: 假设为3
 * 输出: 10 个输出 0~9， 每个输出的值是个小于1的数，表示概率，概率最大的值是表示是最有可能的数字
 */
@main
def testNumberRecogonized(): Unit ={
  //
}