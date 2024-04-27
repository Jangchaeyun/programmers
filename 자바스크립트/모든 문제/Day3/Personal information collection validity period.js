function solution(today, terms, privacies) {
  let current = today.split(".").map((v) => +v);

  let termsKind = {};

  terms.forEach((v) => {
    let [kind, period] = v.split(" ");
    termsKind[kind] = period;
  });

  let result = [];

  privacies.forEach((v, i) => {
    let [past, kind] = v.split(" ");
    let [y, m, d] = past.split(".").map((v) => +v);
    m += +termsKind[kind];
    d--;

    if (m > 12) {
      if (m % 12 === 0) {
        y += parseInt(m / 12) - 1;
        m = 12;
      } else {
        y += parseInt(m / 12);
        m = m % 12;
      }
    }
    if (d === 0) {
      m--;
      d = 28;
      if (m === 0) {
        y--;
        m = 12;
      }
    }
    if (y < current[0]) {
      result.push(i + 1);
      return;
    } else if (y === current[0]) {
      if (m < current[1]) {
        result.push(i + 1);
        return;
      } else if (m === current[1]) {
        if (d < current[2]) {
          result.push(i + 1);
          return;
        }
      }
    }
  });
  return result;
}
