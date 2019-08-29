// SELECT CVS
const cvs = document.getElementById("game");//canvas
const ctx = cvs.getContext("2d");//context

// GAME VARS AND CONSTS
let frames = 0;
const DEGREE = Math.PI/180; //원주율(3.14...)/180 => 1도의 값

// load 스프라이트 이미지
const sprite = new Image();
sprite.src = "img/image.png";//스프라이트 이미지 경로

// load 사운드
const SCORE_S = new Audio();
SCORE_S.src = "audio/sfx_point.wav";

const FLAP = new Audio();
FLAP.src = "audio/sfx_flap.wav";

const HIT = new Audio();
HIT.src = "audio/sfx_hit.wav";

const SWOOSHING = new Audio();
SWOOSHING.src = "audio/sfx_swooshing.wav";

const DIE = new Audio();
DIE.src = "audio/sfx_die.wav";

const BGM = new Audio();
BGM.src = "audio/bgm.wav"

const BGM2 = new Audio();
BGM2.src = "audio/bgm2.wav"

const BGM3 = new Audio();
BGM3.src = "audio/bgm3.wav"

// 게임 상태
const state = {
    current : 0, //현재상태 0
    getReady : 0, // 준비 0
    game : 1, //게임중 1
    over : 2 //게임오버 2
}

// 시작 버튼
const startBtn = {//startBtn범위
    x : 0,
    y : 0,
    w : 1920,
    h : 1080
}

// 게임 조작 관련
cvs.addEventListener("click", function(evt){
    switch(state.current){//현재상태가
        case state.getReady://준비 상태일때
            state.current = state.game;//게임중으로 변경
            BGM.play();//브금 플레이
            SWOOSHING.play();//소리재생
            BGM2.pause();
            BGM2.currentTime=0;
            BGM3.pause();
            BGM3.currentTime=0;
            
            break;

        case state.game://게임중일때 
            if(jellyfish.y - jellyfish.radius <= 0) return;//만약 해파리위치 (y좌표) - 해파리의 반지름이 0보다 작으면 return
            jellyfish.flap();//해파리 날기
            FLAP.play();//소리
            net.flap();
            break;

        case state.over://게임오버일때
            let rect = cvs.getBoundingClientRect();//캔버스의 위치와 크기값 가져오
            let clickX = evt.clientX - rect.left;
            let clickY = evt.clientY - rect.top;
            BGM2.pause();
            BGM2.currentTime=0;
            BGM3.play();
            //BGM2.play();
            
            // 시작 버튼을 누를때 체크
            if(clickX >= startBtn.x && clickX <= startBtn.x + startBtn.w && clickY >= startBtn.y && clickY <= startBtn.y + startBtn.h){
                
                pipes.reset();
                jellyfish.speedReset();
                net.speedReset();
                score.reset();
                state.current = state.getReady;
                
            }
            break;
    }
});


// BACKGROUND
const bg = {
    sX : 0,
    sY : 0,
    w : 539,
    h : 959,
    x : 0,
    y : 0,
    
    draw : function(){
        ctx.drawImage(sprite, this.sX, this.sY, this.w, this.h, this.x, this.y, this.w, this.h);
        
        ctx.drawImage(sprite, this.sX, this.sY, this.w, this.h, this.x + this.w, this.y, this.w, this.h);
    }
    
}

// FOREGROUND
const fg = {
    sX: 602,
    sY: 0,
    w: 538,
    h: 205,
    x: 0,
    y: cvs.height - 205,
    
    dx : 5,
    
    draw : function(){
        ctx.drawImage(sprite, this.sX, this.sY, this.w, this.h, this.x, this.y, this.w, this.h);
        
        ctx.drawImage(sprite, this.sX, this.sY, this.w, this.h, this.x + this.w, this.y, this.w, this.h);
    },
    
    update: function(){
        if(state.current == state.game){
            this.x = (this.x - this.dx)%(this.w/2);
        }
    }
}

// jellyfish
const jellyfish = {
    animation : [
        {sX: 541, sY : 0},
        {sX: 541, sY : 62},
        {sX: 541, sY : 124},
        {sX: 541, sY : 0}
    ],
    x : 60,//해파리 x위치
    y : 150,//해파리 y위치
    w : 61,
    h : 61,
    
    radius : 12,//반지름 해파리 크기 범위를 지정해줌 (충돌 관련)
    
    frame : 0,
    
    gravity : 0.25,
    jump : 4.6,
    speed : 0,
    rotation : 0,
    
    draw : function(){
        let jellyfish = this.animation[this.frame];
        
        ctx.save();
        ctx.translate(this.x, this.y);
        ctx.rotate(this.rotation);
        ctx.drawImage(sprite, jellyfish.sX, jellyfish.sY, this.w, this.h,- this.w/2, - this.h/2, this.w, this.h);
        
        ctx.restore();
    },
    
    flap : function(){//날기
        this.speed = - this.jump;//속도= 점프 - 속도
    },
    
    update: function(){
        // 게임 준비 상대일때는 해파리가 천천히 동작이 바뀜
        this.period = state.current == state.getReady ? 10 : 5;
        // 1프레임씩 증가
        this.frame += frames%this.period == 0 ? 1 : 0;
        // 프레임은 0에서 4까지 갔다가 다시 0으로 감
        this.frame = this.frame%this.animation.length;
        
        if(state.current == state.getReady){
            this.y = 370; // 게임 종료후 해파리 위치 재설정
            this.rotation = 0 * DEGREE;
        }else{
            this.speed += this.gravity;
            this.y += this.speed;
            
            if(this.y + this.h/2 >= cvs.height - fg.h){
                this.y = cvs.height - fg.h - this.h/2;
                if(state.current == state.game){
                    state.current = state.over;
                    DIE.play();
                    BGM.pause();
                    BGM.currentTime=0;
                    BGM2.play();
                }
            }
            
            // 만약 해파리 속도보다 점프가 더 빠르면 해파리가 떨어짐
            if(this.speed >= this.jump){
                this.rotation = 90 * DEGREE;
                this.frame = 1;
            }else{
                this.rotation = -25 * DEGREE;
            }
        }
        
    },
    speedReset : function(){
        this.speed = 0;
    }
}

const net = {
    animation : [
        {sX: 2262, sY : 0},
        {sX: 2359, sY : 0},
        {sX: 2456, sY : 0},
        {sX: 2262, sY : 0}
    ],
    x : 0,//해파리 x위치
    y : 470,//해파리 y위치
    w : 97,
    h : 274,
    
    radius : 0,//반지름 해파리 크기 범위를 지정해줌 (충돌 관련)
    
    frame : 0,
    
    gravity : 0.25,
    jump : 4.6,
    speed : 0,
    rotation : 0,
    
    draw : function(){
        let net = this.animation[this.frame];
        
        ctx.save();
        ctx.translate(this.x, this.y);
        ctx.rotate(this.rotation);
        ctx.drawImage(sprite, net.sX, net.sY, this.w, this.h,- this.w/2, - this.h/2, this.w, this.h);
        
        ctx.restore();
    },
    
    flap : function(){//날기
        this.speed = - this.jump;//속도= 점프 - 속도
    },
    
    update: function(){
        // 게임 준비 상대일때는 해파리가 천천히 동작이 바뀜
        this.period = state.current == state.getReady ? 10 : 5;
        // 1프레임씩 증가
        this.frame += frames%this.period == 0 ? 1 : 0;
        // 프레임은 0에서 4까지 갔다가 다시 0으로 감
        this.frame = this.frame%this.animation.length;
        
        if(state.current == state.getReady){
            this.y = 470; // 게임 종료후 해파리 위치 재설정
            this.rotation = 0 * DEGREE;
        }else{
            this.speed += this.gravity;
            this.y += this.speed;
            
            if(this.y + this.h/2 -140 >= cvs.height - fg.h){
                 this.y = cvs.height - fg.h - this.h/2;
                 this.rotation = 110 * DEGREE;
                if(state.current == state.over){
                    this.y = cvs.height - fg.h - this.h/6;
                }
            }
            
            // 만약 해파리 속도보다 점프가 더 빠르면 해파리가 떨어짐
            if(this.speed >= this.jump){
                //this.rotation = 90 * DEGREE;
                this.frame = 2;
            }else{
                //this.rotation = -25 * DEGREE;
            }
        }
        
    },
    speedReset : function(){
        this.speed = 0;
    }
}

// GET READY
const getReady = {
    sX : 1643,
    sY : 0,
    w : 430,
    h : 514,
    x : cvs.width/2 - 430/2,
    y : 200,
    
    draw: function(){
        if(state.current == state.getReady){
            ctx.drawImage(sprite, this.sX, this.sY, this.w, this.h, this.x, this.y, this.w, this.h);
        }
    }
    
}

// GAME OVER
const gameOver = {
    sX : 1143,
    sY : 0,
    w : 498,
    h : 613,
    x : cvs.width/2 - 499/2,
    y : 200,
    
    draw: function(){
        if(state.current == state.over){
            ctx.drawImage(sprite, this.sX, this.sY, this.w, this.h, this.x, this.y, this.w, this.h);   
        }
    }
    
}

// 장애물
const pipes = {
    position : [],
    
    top : {
        sX : 2074,
        sY : 0
    },
    bottom:{
        sX : 2168,
        sY : 0
    },
    
    w : 93,
    h : 959,
    gap : 100,
    maxYPos : -400,//-
    dx : 8,
    
    draw : function(){
        for(let i  = 0; i < this.position.length; i++){
            let p = this.position[i];
            
            let topYPos = p.y;
            let bottomYPos = p.y + this.h + this.gap;
            
            // top 장애물
            ctx.drawImage(sprite, this.top.sX, this.top.sY, this.w, this.h, p.x, topYPos, this.w, this.h);  
            
            // bottom 장애물
            ctx.drawImage(sprite, this.bottom.sX, this.bottom.sY, this.w, this.h, p.x, bottomYPos, this.w, this.h);  
        }
    },
    
    update: function(){
        if(state.current !== state.game) return;
        
        if(frames%100 == 1){
            this.position.push({
                x : cvs.width,//x끝
                y : this.maxYPos * ( Math.random()*1 + 1) //여기 확인하기
            });
        }
        for(let i = 0; i < this.position.length; i++){
            let p = this.position[i];
            
            let bottomPipeYPos = p.y + this.h + this.gap;
            
            // 충돌관련
            // TOP 장애물
            if(jellyfish.x + jellyfish.radius > p.x && jellyfish.x - jellyfish.radius < p.x + this.w && jellyfish.y + jellyfish.radius > p.y && jellyfish.y - jellyfish.radius < p.y + this.h){
                state.current = state.over;
                HIT.play();
                BGM.pause();
                BGM.currentTime=0;
                BGM2.play();
            }
            // BOTTOM 장애물
            if(jellyfish.x + jellyfish.radius > p.x && jellyfish.x - jellyfish.radius < p.x + this.w && jellyfish.y + jellyfish.radius > bottomPipeYPos && jellyfish.y - jellyfish.radius < bottomPipeYPos + this.h){
                state.current = state.over;
                HIT.play();
                BGM.pause();
                BGM.currentTime=0;
                BGM2.play();
            }
            
            // 장애물을 왼쪽으로 이동시키기
            p.x -= this.dx;
            
            // 장애물이 캔버스를 넘어가면 배열에서 장애물을 삭제
            if(p.x + this.w <= 0){
                this.position.shift();
                score.value += 1;
                SCORE_S.play();
                score.best = Math.max(score.value, score.best);
                localStorage.setItem("best", score.best);
            }
        }
    },
    
    reset : function(){
        this.position = [];
    }
    
}

// 점수
const score= {
    best : parseInt(localStorage.getItem("best")) || 0,
    value : 0,
    
    draw : function(){
        ctx.fillStyle = "#FFF";
        ctx.strokeStyle = "#000";
        
        if(state.current == state.game){
            ctx.lineWidth = 2;
            ctx.font = "35px Teko";
            ctx.fillText(this.value, cvs.width/2, 50);
            ctx.strokeText(this.value, cvs.width/2, 50);
            
        }else if(state.current == state.over){
            // SCORE VALUE
            ctx.font = "25px Teko";
            ctx.fillText(this.value, 270, 500);
            ctx.strokeText(this.value, 270, 500);
            // BEST SCORE
            ctx.fillText(this.best, 270, 620);
            ctx.strokeText(this.best, 270, 620);
        }
    },
    
    reset : function(){
        this.value = 0;
    }
}

// DRAW
function draw(){
    ctx.fillStyle = "#6dcff6";
    ctx.fillRect(0, 0, cvs.width, cvs.height);
    
    bg.draw();
    pipes.draw();
    fg.draw();
    net.draw();
    jellyfish.draw();
    getReady.draw();
    gameOver.draw();
    score.draw();
}

// UPDATE
function update(){
    jellyfish.update();
    net.update();
    fg.update();
    pipes.update();
}

// LOOP
function loop(){
    update();
    draw();
    frames++;
    
    requestAnimationFrame(loop);
}

loop();


