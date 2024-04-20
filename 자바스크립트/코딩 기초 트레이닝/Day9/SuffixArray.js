function solution(my_string) {
  const strlen = my_string.length;
  return [...my_string]
    .reduce((acc, cur, idx) => {
      const curStr = my_string.slice(idx, strlen);
      return [...acc, curStr];
    }, [])
    .sort();
}
