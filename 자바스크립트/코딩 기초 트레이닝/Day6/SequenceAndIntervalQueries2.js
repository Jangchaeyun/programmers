function solution(arr, queries) {
  const result = [];

  for (let i = 0; i < queries.length; i++) {
    const [s, e, k] = queries[i];
    const subArray = arr.slice(s, e + 1);
    const filteredArray = subArray.filter((num) => num > k);

    if (filteredArray.length === 0) {
      result.push(-1);
    } else {
      const minVal = Math.min(...filteredArray);
      result.push(minVal);
    }
  }
  return result;
}
