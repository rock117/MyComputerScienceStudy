package cs.fnlearning

/**
 *  使用foldLeft/foldRight 實現 map/flatMap/filter
 */


def map[A, B](list: List[A])(func: A => B): List[B] =
    list.foldRight(List.empty[B]) { (item, accum) =>
        func(item) :: accum
    }

def flatMap[A, B](list: List[A])(func: A => List[B]): List[B] =
    list.foldRight(List.empty[B]) { (item, accum) =>
        func(item) ::: accum
    }


def filter[A](list: List[A])(func: A => Boolean): List[A] =
    list.foldRight(List.empty[A]) { (item, accum) =>
        if(func(item)) item :: accum else accum
    }