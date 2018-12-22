package utils

func QuickSort(values []int) {
	length := len(values)
	if length <= 1 {
		return
	}
	mid, i := values[0], 1
	head, tail := 0, length-1

	for head < tail {
		if values[i] > mid {
			values[i], values[tail] = values[tail], values[i]
			tail--
		} else {
			values[i], values[head] = values[head], values[i]
			head++
			i++
		}
	}
	// 分水岭左右的元素递归做同样处理
	QuickSort(values[:head])
	QuickSort(values[head+1:])
}
