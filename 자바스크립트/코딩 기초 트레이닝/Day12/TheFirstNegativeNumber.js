function solution(num_list) {
  const find = num_list.findIndex((a) => a < 0);
  if (find >= 0) {
    return find;
  } else {
    return -1;
  }
}
