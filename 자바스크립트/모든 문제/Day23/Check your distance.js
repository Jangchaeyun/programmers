function solution(places) {
  const result = [];
  for (const place of places) {
    let isSafePlace = 1;

    for (let i = 0; i < 5; i++) {
      for (let j = 0; j < 5; j++) {
        if (place[i][j] === "P") {
          if (!isSafe(place, i, j)) {
            isSafePlace = 0;
            break;
          }
        }
      }

      if (!isSafePlace) {
        break;
      }
    }

    result.push(isSafePlace);
  }

  return result;
}

function isSafe(place, x, y) {
  const dx = [0, 0, 1, -1];
  const dy = [1, -1, 0, 0];

  for (let i = 0; i < 4; i++) {
    const nx = x + dx[i];
    const ny = y + dy[i];

    if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
      if (place[nx][ny] === "P") {
        return false;
      } else if (place[nx][ny] === "O") {
        for (let j = 0; j < 4; j++) {
          const nnx = nx + dx[j];
          const nny = ny + dy[j];

          if (nnx >= 0 && nnx < 5 && nny >= 0 && nny < 5) {
            if (nnx !== x || nny !== y) {
              if (place[nnx][nny] === "P") {
                return false;
              }
            }
          }
        }
      }
    }
  }

  return true;
}
