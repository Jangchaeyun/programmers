function solution(strList) {
    var answer = [];
    for (let i = 0; i < strList.length; i++) {
        answer.push(strList[i].length);
    }
    return answer;
}