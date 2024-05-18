function solution(arr) {
  const answer = [0, 0];

  function check(array) {
    if (array.length === 1) return answer[array[0][0]]++;

    let flag = true;
    for (let i = 0; i < array.length; i++) {
      for (let j = 0; j < array.length; j++) {
        if (array[0][0] !== array[i][j]) {
          flag = false;
          break;
        }
      }
    }

    if (flag) return answer[array[0][0]]++;

    check(
      array
        .filter((_, index) => index < array.length / 2)
        .map((value, index) =>
          value.filter((_, index) => index < array.length / 2)
        )
    );

    check(
      array
        .filter((_, index) => index >= array.length / 2 && index < array.length)
        .map((value, index) =>
          value.filter((_, index) => index < array.length / 2)
        )
    );

    check(
      array
        .filter((_, index) => index < array.length / 2)
        .map((value, index) =>
          value.filter(
            (_, index) => index >= array.length / 2 && index < array.length
          )
        )
    );

    check(
      array
        .filter((_, index) => index >= array.length / 2 && index < array.length)
        .map((value, index) =>
          value.filter(
            (_, index) => index >= array.length / 2 && index < array.length
          )
        )
    );
  }

  check(arr);

  return answer;
}
