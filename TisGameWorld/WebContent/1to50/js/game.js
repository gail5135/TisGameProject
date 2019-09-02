const num1 = [];
for (let i = 1; i <= 25; i++) {
    num1[num1.length] = i;
}

const num2 = [];
for (let i = 26; i <= 50; i++) {
    num2[num2.length] = i;
}

var getRandomArr = function (arr) {
    const randomArr = [];

    for (let i = 1; i <= 25; i++) {
        let ranIdx = Math.floor(Math.random() * arr.length);
        randomArr[randomArr.length] = arr[ranIdx];
        arr.splice(ranIdx, 1);
    }

    return randomArr;
}


//DocumentReady
$(function () {
    const randomArr1 = getRandomArr(num1);

    for (let i = 0; i < randomArr1.length; i++) {
        $('div.row > div.col'+(i+1)).html(randomArr1[i]);        
    }

    let cnt = 1;

    $('div.col').click(function () {
        if($(this).html() == cnt){
            // console.log('good');
            $(this).css('display', 'hide');
            cnt++;
        }
    });

});//////////DocumentReady