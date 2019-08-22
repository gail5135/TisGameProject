var game;
var gameWidth = 720;
var gameHeight = 1280;
var noteTimer = 8500;
var noteFlag = 0;
var notes = [];
var noteTimeTable = [
                    [8800,1], [9200,3], [9500,1], [9900,3], [10300,1], [10600,3], 
                    [11000,1], [11400,3], [11700,1], [12100,3], [12500,1], [12800,3], 
                    [13200,1], [13600,3], [13800,1], [14000,1], [14300,3], [14700,5], 
                    [18300,1], [18700,3], [19200,1], [19400,3], [19600,1], [19800,3], [20100,1]
                    ];
var greenBar;
var yellowBarOne;
var yellowBarTwo;
var noteQ;
var noteW;
var noteO;
var noteP;
var keys;
var hit;

// note class
class note extends Phaser.GameObjects.Image {
    constructor(scene, x, y, texture) {
        super(scene, x, y, texture);

        this.state = 1; // 1: alive, 0: dead
        this.fallingFlag = true;
        
        scene.add.existing(this);
    }

    preUpdate(){
        if(this.fallingFlag){
            this.y += 10;
        }
        if(this.y > gameHeight+10 && this.flag){
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
        this.load.audio('selectedMusic', "assets/don't_look_back_in_anger.mp3"); 	

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
        console.log(this);
        console.log(this.scene.start("Title"));
    }
}

class title extends Phaser.Scene{
    constructor(){
        super("Title");
    }

    preload(){
        
    }
    
    create(){
        // this.cameras.backgroundColor.setTo('#ffffff');
    
        var txt = this.add.text(gameWidth/2, 200, 'This is MyRhythmGame');
        txt.setColor('#ff0000');

        var logo = this.add.image(gameWidth/2, gameHeight/4, 'logo');
        var startButton = this.add.image(gameWidth/2, (gameHeight/4)*3, 'startButton');

        startButton.setInteractive().on('pointerdown', function(pointer, localX, localY, event){
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
    }
    
    create(){
        var music = this.sound.add('selectedMusic');
        // hit = this.sound.add('hit');
        // music.play({ delay: 1.5});
        
        keys = this.input.keyboard.addKeys({
            Q: 'Q',
            W: 'W',
            O: 'O',
            P: 'P'
        });

        yellowBarOne = this.add.rectangle(360, 1025, 720, 50, 0xdbd81a);
        greenBar = this.add.rectangle(360, 1100, 720, 75, 0x25b357);
        yellowBarTwo = this.add.rectangle(360, 1175, 720, 50, 0xdbd81a);

        if(music.play({ delay: 1.5})){
            this.time.addEvent({
                delay: 100, // ms
                callback: ()=>{
                    noteTimer+=100;
                    if(noteTimeTable.length > noteFlag){
                        if(noteTimeTable[noteFlag][0] === noteTimer){
                            if(noteTimeTable[noteFlag][1] === 1){
                                let noteQ = new note(this, (gameWidth/8)*1, 100, 'note');
                                this.pressKey(keys.Q, noteQ, this);
                                notes.push(noteQ);
                                noteFlag++;
                            }
                            else if(noteTimeTable[noteFlag][1] === 3){
                                let noteW = new note(this, (gameWidth/8)*3, 100, 'note');
                                this.pressKey(keys.W, noteW, this);
                                notes.push(noteW);
                                noteFlag++;
                            }
                            else if(noteTimeTable[noteFlag][1] === 5){
                                let noteO = new note(this, (gameWidth/8)*5, 100, 'note');
                                this.pressKey(keys.O, noteO, this);
                                notes.push(noteO);
                                noteFlag++;
                            }
                            else if(noteTimeTable[noteFlag][1] === 7){
                                let noteP = new note(this, (gameWidth/8)*7, 100, 'note');
                                this.pressKey(keys.P, noteP, this);
                                notes.push(noteP);
                                noteFlag++;
                            }
                        }  
                    }
                },
                //args: [],
                callbackScope: this,
                loop: true
            });
        }
        // this.scene.start('GameOver');
    }

    pressKey(key, note, scene){
        key.on('down', function(event) {
            if(note.y > 1025 && note.y < 1200){
                note.stopFalling();
                scene.tweens.add({
                    targets: note,
                    scaleX: 1.5,
                    scaleY: 1.5,
                    alpha: 0,
                    duration: 500,
                    onComplete: ()=>{
                        notes.shift();
                        note.destroy(); 
                    }
                }, scene);
            }
        });
    }
}

class gameOver extends Phaser.Scene{
    constructor(){
        super("GameOver");
    }
    
    create(){

    }
}
// Run Game
window.onload = function () {
	var config = {
		type: Phaser.AUTO,
		backgroundColor: '#000000',
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
		scene: [bootGame, title, playGame]
	};
	game = new Phaser.Game(config);
	resize();
	window.addEventListener("resize", resize, false);
}

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