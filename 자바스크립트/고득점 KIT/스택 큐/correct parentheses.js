function solution(s) {
  return ![...s].reduce((acc, cur) => {
    if (acc[acc.length - 1] === "(" && cur === ")") {
      acc.pop();
    } else {
      acc.push(cur);
    }
    return acc;
  }, []).length;
}
