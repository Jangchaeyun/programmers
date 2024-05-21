function solution(m, musicinfos) {
  var answer = "(None)";
  var hashMap = new Map();
  m = m.replaceAll("A#", "z");
  m = m.replaceAll("G#", "m");
  m = m.replaceAll("F#", "n");
  m = m.replaceAll("D#", "v");
  m = m.replaceAll("C#", "p");
  m = m.replaceAll("B#", "s");
  m = m.replaceAll("E#", "t");

  musicinfos.forEach((element) => {
    var div = element.split(",");

    div[3] = div[3].replaceAll("A#", "z");
    div[3] = div[3].replaceAll("G#", "m");
    div[3] = div[3].replaceAll("F#", "n");
    div[3] = div[3].replaceAll("D#", "v");
    div[3] = div[3].replaceAll("C#", "p");
    div[3] = div[3].replaceAll("B#", "s");
    div[3] = div[3].replaceAll("E#", "t");
    var hour = Number(div[1].split(":")[0]);
    hour -= Number(div[0].split(":")[0]);
    var minute = Number(div[1].split(":")[1]);
    minute -= Number(div[0].split(":")[1]);
    if (minute < 0) {
      hour--;
      minute += 60;
    }
    time = hour * 60 + minute;
    var code = "";
    var index = 0;

    while (time--) {
      if (index >= div[3].length) {
        index = 0;
      }
      code += div[3][index++];
    }

    hashMap.set(div[2], [code, hour * 60 + minute]);
  });

  var checkTime = 0;
  for (let [key, value] of hashMap) {
    if (value[0].includes(m)) {
      if (checkTime < value[1]) {
        answer = key;
        checkTime = value[1];
      }
    }
  }
  return answer;
}
