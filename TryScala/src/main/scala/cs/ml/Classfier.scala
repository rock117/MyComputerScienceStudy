package cs.ml
// 简单的分类器 y = A*x
// t = (A + dA)*x => E = (dA)*x //误差E为A增加dA的值
//
//  有一组样本数据(x1, y1), (x2, y2),.. , (xn,yn), 如何确定 合适的A
//
type XY = (Double, Double)
type SampleData = Array[XY]

def calcDelta(sample: XY, real: XY):Double = {
  real._2 - sample._2
}