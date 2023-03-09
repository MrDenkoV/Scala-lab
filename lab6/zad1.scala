// Wcześniejsze zadanie to dokładnie to samo co jest wypisane w zadaniu, tylko zamiast intów dajemy stringi
val first100div3 = for (i <- (1 to 300).toList if i % 3 == 0) yield i
first100div3.length
first100div3.head
first100div3.tail.head
first100div3.last