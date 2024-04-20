function solution(my_string) {
  let smallString = my_string.toLowerCase();
  return smallString.split("").sort().join("");
}
