function solution(mystring) {
  return [...mystring].map((v) => (v < "l" ? "l" : v)).join("");
}
