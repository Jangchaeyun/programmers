function solution(arrows) {
  let chck = {};
  let direc = {};
  let q = [];
  chck["0,0"] = 0;
  q.push([0, 0]);
  let x = 0;
  let y = 0;
  let ans = 0;

  const dx = [0, 1, 1, 1, 0, -1, -1, -1];
  const dy = [-1, -1, 0, 1, 1, 1, 0, -1];

  for (let i of arrows) {
    for (let j = 0; j < 2; j++) {
      let currx = x + dx[i];
      let curry = y + dy[i];
      chck[currx + "," + curry] = 0;
      direc[x + "," + y + "," + currx + "," + curry] = 0;
      direc[currx + "," + curry + "," + x + "," + y] = 0;
      q.push([currx, curry]);
      x = currx;
      y = curry;
    }
  }
  let pos = q.shift();
  x = pos[0];
  y = pos[1];
  chck[x + "," + y] = 1;

  while (q.length > 0) {
    let newPos = q.shift();
    let nx = newPos[0];
    let ny = newPos[1];

    if (chck[nx + "," + ny] == 1) {
      if (direc[x + "," + y + "," + nx + "," + ny] == 0) {
        ans += 1;
        direc[x + "," + y + "," + nx + "," + ny] = 1;
        direc[nx + "," + ny + "," + x + "," + y] = 1;
      }
    } else {
      chck[nx + "," + ny] = 1;
      direc[x + "," + y + "," + nx + "," + ny] = 1;
      direc[nx + "," + ny + "," + x + "," + y] = 1;
    }
    x = nx;
    y = ny;
  }
  return ans;
}
