var game;
var gameWidth = 720;
var gameHeight = 1280;

var colorConfig = {
    title: '#ffffff',
    notice: '#ffffff',
    startText: '#ffffff',
    note: 0xffffff, // Don't use '# 'color number
    validArea: '#000000',
    guideKey: '#bfbfbf',
    score: '#ffc105',
}

var validAreaX = 0;
var validAreaWidth = gameWidth;
var validAreaY = (gameHeight/10)*9;
var validAreaHeight = gameHeight/10;

var notes = [];
var noteTimer = 8500;
var noteFlag = 0;
var noteTimeTable = [
                    [8800,1], [9200,3], [9500,1], [9900,3], [10300,1], [10600,3], 
                    [11000,1], [11400,3], [11700,1], [12100,3], [12500,1], [12800,3], 
                    [13200,1], [13600,3], [13800,1], [14000,1], [14300,3], [14700,5], 
                    [18300,1], [18700,3], [19200,1], [19400,3], [19600,1], [19800,3], [20100,1]
                    ];

var score;
var keys;




// note class (rectangle)
class note extends Phaser.GameObjects.Rectangle {
    constructor(scene, x, y, width, height, position) {
        super(scene, x, y, width, height, colorConfig.note);
        
        this.state = 1; // 1: alive, 0: dead
        this.position = position; // line position
        this.fallingFlag = true;
        this.disappear = this.scene.tweens.add({
                            targets: this,
                            paused: true,
                            scaleX: 1.5,
                            scaleY: 1.5,
                            alpha: 0,
                            duration: 300,
                            onComplete: ()=>{
                                notes.shift();
                                this.destroy();
                                // console.log(notes.length);
                            }
                        }, this.scene);

        this.setDepth(2);
        scene.add.existing(this);
    }

    // falling the note
    preUpdate(){
        if(this.fallingFlag){
            this.y += 10;
        }
        else{
            this.disappear.play();
        }
        
        if(this.fallingFlag && this.y > gameHeight+100){
            notes.shift()
            this.destroy();         
        }
    }

    stopFalling(){
        this.fallingFlag = false;
    }
}


// Load Resources Scene
class bootGame extends Phaser.Scene{
    constructor(){
        super("BootGame");
    }

    preload(){
        // Load Assets        
        this.load.image("note", "assets/note.png");
        this.load.image('logo', 'assets/logo_sample.png');
        this.load.image('startButton','assets/start.png');
        this.load.image("backGroundImage", "assets/noel.jpg");
        this.load.image("home", "assets/home.png");
        this.load.image("reset", "assets/reset.png");
        this.load.audio('selectedMusic', "assets/don't_look_back_in_anger_cut_version.mp3"); 	

        // Make & Run Loading Bar (Progress Bar)
        var progressBar = this.add.graphics();
        var progressBox = this.add.graphics();
        progressBox.fillStyle(0x222222, 0.8);
        progressBox.fillRect(gameWidth/4, gameHeight/4, gameWidth/2, 50);
        
        var loadingText = this.make.text({
            x: gameWidth / 2,
            y: gameHeight / 2 - 50,
            text: 'Loading...',
            style: {
                font: '30px monospace',
                fill: '#ffffff'
            }
        });
        loadingText.setOrigin(0.5, 0.5);
        
        var percentText = this.make.text({
            x: gameWidth / 2,
            y: gameHeight / 2 - 5,
            text: '0%',
            style: {
                font: '26px monospace',
                fill: '#ffffff'
            }
        });
        percentText.setOrigin(0.5, 0.5);
        
        var assetText = this.make.text({
            x: gameWidth / 2,
            y: gameHeight / 2 + 50,
            text: '',
            style: {
                font: '26px monospace',
                fill: '#ffffff'
            }
        });
        assetText.setOrigin(0.5, 0.5);
        
        this.load.on('progress', function (value) {
            percentText.setText(parseInt(value * 100) + '%');
            progressBar.clear();
            progressBar.fillStyle(0xffffff, 1);
            progressBar.fillRect(gameWidth/4+10, gameHeight/4+10, (gameWidth/2-20) * value, 30);
        });
        
        this.load.on('fileprogress', function (file) {
            assetText.setText('Loading asset: ' + file.key);
        });

        this.load.on('complete', function () {
            progressBar.destroy();
            progressBox.destroy();
            loadingText.destroy();
            percentText.destroy();
            assetText.destroy();
        });
    }
    create(){
        this.scene.start("Title");
    }
}

// Title Page
class title extends Phaser.Scene{
    constructor(){
        super("Title");
    }

    create(){
        // this.cameras.main.setBackgroundColor("#000000");

        var titleText = this.add.text(gameWidth/2, gameHeight/4, "HC_RhythmGame");
        titleText.setFontSize(70);
        titleText.setColor(colorConfig.title);
        titleText.setOrigin(0.5);

        var notice = this.add.text(gameWidth/2, (gameHeight/4)*(2.5), "Click Start or Press Spacebar");
        notice.setFontSize(30);
        notice.setOrigin(0.5);
        notice.setColor(colorConfig.notice);

        var startButton = this.add.text(gameWidth/2, (gameHeight/4)*3, 'Start');
        startButton.setFontSize(50);
        startButton.setOrigin(0.5);
        startButton.setColor(colorConfig.startText);
        startButton.setInteractive().on('pointerdown', function(pointer, localX, localY, event){
            this.scene.scene.start("PlayGame");
        });

        this.input.keyboard.on('keydown-SPACE', function (event) {
            this.scene.scene.start("PlayGame");
        });
    }
}

// Play MyRhythmGame Scene
class playGame extends Phaser.Scene{
    constructor(){
        super("PlayGame");
    }

    preload(){
        // Nothing yet
        keys = this.input.keyboard.addKeys({
            Q: 'Q',
            W: 'W', 
            O: 'O', 
            P: 'P'
        });
    }
    
    create(){
        
        // Attach BackgroundImage        
        this.add.image(gameWidth/2, gameHeight/2, 'backGroundImage');

        // Make Score
        score = this.add.text(gameWidth/2, gameHeight/8, "0");
        score.setFontSize(120);
        score.setColor(colorConfig.score);
        score.setOrigin(0.5);
        score.setVisible(false);
        score.setDepth(5);


        // Make CountDown to start game;
        var countDown = this.add.text(gameWidth/2, gameHeight/8, "3");
        countDown.setFontSize(120);
        countDown.setColor('#ffffff');
        countDown.setOrigin(0.5);

        // Make KeyGuide for each lines;
        this.makeKeyGuideText("Q", (gameWidth/8), (gameHeight/20)*19);
        this.makeKeyGuideText("W", (gameWidth/8)*3, (gameHeight/20)*19);
        this.makeKeyGuideText("O", (gameWidth/8)*5, (gameHeight/20)*19);
        this.makeKeyGuideText("P", (gameWidth/8)*7, (gameHeight/20)*19);

        var judgeBar = this.add.rectangle(validAreaX, validAreaY, validAreaWidth, validAreaHeight, colorConfig.validArea);
        judgeBar.setOrigin(0,0);
        judgeBar.setDepth(1);

        this.pressKey();
        
        var music = this.sound.add('selectedMusic');
        
        var countDownTimer = this.time.addEvent({
            delay: 750,                // ms
            callback: ()=>{
                this.countDownGameStart(countDown, countDownTimer.getRepeatCount());
                if(countDownTimer.getRepeatCount() === 0){
                    countDown.destroy();
                    score.setVisible(true);
                    this.mainLoop(music);
                }
            },
            callbackScope: this,
            repeat: 3
        }); 
    }

    mainLoop(music){
        music.play({ delay: 1.75})       
        var musicLoop = this.time.addEvent({
            delay: 100, // ms
            callback: ()=>{
                noteTimer+=100;
                if(noteTimer > 20000){
                    music.stop();
                    this.scene.start("GameOver");
                }
                else if(noteTimeTable.length > noteFlag){
                    if(noteTimeTable[noteFlag][0] === noteTimer){
                        if(noteTimeTable[noteFlag][1] === 1){
                            notes.push(new note(this, (gameWidth/8), 100, gameWidth/4, 50, 1));
                            noteFlag++;
                        }
                        else if(noteTimeTable[noteFlag][1] === 3){
                            notes.push(new note(this, (gameWidth/8)*3, 100, gameWidth/4, 50, 2));
                            noteFlag++;
                        }
                        else if(noteTimeTable[noteFlag][1] === 5){
                            notes.push(new note(this, (gameWidth/8)*5, 100, gameWidth/4, 50, 3));
                            noteFlag++;
                        }
                        else if(noteTimeTable[noteFlag][1] === 7){
                            notes.push(new note(this, (gameWidth/8)*7, 100, gameWidth/4, 50, 4));
                            noteFlag++;
                        }
                       
                    }  
                }     
            },
            callbackScope: this,
            loop: true
        });   
    }

    countDownGameStart(text, value){
        if(text.text > 1){
            text.setText(value-1);
        }
        else {
            text.setFontSize(75);
            text.setText("Game Start!!")
        }
    }

    pressKey(){
        this.input.keyboard.on('keydown', function (event) {
            if(event.key === 'q'){
                this.scene.judgeNote(1);
            }
            else if(event.key === 'w'){
                this.scene.judgeNote(2);
            }
            else if(event.key === 'o'){
                this.scene.judgeNote(3);
            }
            else if(event.key === 'p'){
                this.scene.judgeNote(4);
            }
            else{
                console.log('Not QWOP');
            }
        });
    }

    judgeNote(line){
        notes.forEach(element => {
            if(element.position === line && element.y > validAreaY){
                element.stopFalling();
                score.setText(parseInt(score.text) + 10);
            }
        });
    }

    makeKeyGuideText(value, x, y){
        let text = this.add.text(x, y, value);
        text.setFontSize(50);
        text.setColor(colorConfig.guideKey);
        text.setOrigin(0.5);
        text.setDepth(3);
    }
}

class gameOver extends Phaser.Scene{
    constructor(){
        super("GameOver");
    }
    
    create(){
        // Show Score of The Game
        var result = this.add.text(gameWidth/2, gameHeight/5, score.text);
        result.setOrigin(0.5);
        result.setColor(colorConfig.score);
        result.setFontSize(200);

        // Make HomeIcon
        var homeIcon = this.add.image(gameWidth/3, (gameHeight/5)*2, "home");
        homeIcon.setOrigin(0.5);
        homeIcon.setInteractive().on('pointerdown', function(pointer, localX, localY, event){
            this.scene.scene.start("Title");
        });

        // Make ResetIcon
        var resetIcon = this.add.image((gameWidth/3)*2, (gameHeight/5)*2, "reset");
        resetIcon.setOrigin(0.5);
        resetIcon.setInteractive().on('pointerdown', function(pointer, localX, localY, event){
            this.scene.scene.start("PlayGame");
        });

        // Show rankings
        for(let i = 0; i < 5; ++i){
            var record = this.add.text(gameWidth/2, (gameHeight/20)*(15-i), "Record");
            record.setFontSize(30);
            record.setColor('#ffffff');
            record.setOrigin(0.5);
        }
    }
}

// Run Game
window.onload = function () {
	var config = {
		type: Phaser.AUTO,
		backgroundColor: 0x123456,
        scale: {
            mode: Phaser.Scale.FIT,
            autoCenter: Phaser.Scale.CENTER_BOTH,
            parent: "thegame",
            width: gameWidth,
            height: gameHeight
        },
		physics: {
			default: 'arcade',
			arcade: {
				debug: false
			}
		},
		scene: [bootGame, title, playGame, gameOver]
	};
    game = new Phaser.Game(config);
	window.resize();
	window.addEventListener("resize", resize, false);
}

// Resize by ratio
function resize() {
	var canvas = document.querySelector("canvas");
	var windowWidth = window.innerWidth;
	var windowHeight = window.innerHeight;
	var windowRatio = windowWidth / windowHeight;
	var gameRatio = game.config.width / game.config.height;
	if (windowRatio < gameRatio) {
		canvas.style.width = windowWidth + "px";
		canvas.style.height = (windowWidth / gameRatio) + "px";
	} else {
		canvas.style.width = (windowHeight * gameRatio) + "px";
		canvas.style.height = windowHeight + "px";
	}
}