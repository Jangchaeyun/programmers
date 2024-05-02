function solution(numbers, hand) {
    let answer = '';
    let leftThumb = 10, rightThumb = 12;

    for (let i = 0; i < numbers.length; i++) {
        if (numbers[i] % 3 === 1) {
            answer += 'L';
            leftThumb = numbers[i];
        }
        else if (numbers[i] % 3 === 0 && numbers[i] !== 0) {
            answer += 'R';
            rightThumb = numbers[i];
        }
        else {
            if (numbers[i] === 0) numbers[i] = 11;
            
            let tmpL = Math.abs(numbers[i] - leftThumb);
            let tmpR = Math.abs(numbers[i] - rightThumb);

            tmpL = Math.floor(tmpL / 3) + Math.floor(tmpL % 3);
            tmpR = Math.floor(tmpR / 3) + Math.floor(tmpR % 3);

            if (tmpL === tmpR) {
                if (hand === 'right') {
                    answer += 'R';
                    rightThumb = numbers[i];
                }
                else {
                    answer += 'L';
                    leftThumb = numbers[i];
                }
            }
            else if (tmpL < tmpR) {
                answer += 'L';
                leftThumb = numbers[i];
            }
            else {
                answer += 'R';
                rightThumb = numbers[i];
            }
        }
    }
    return answer;
}