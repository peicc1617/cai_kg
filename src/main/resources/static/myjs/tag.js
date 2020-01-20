class Tag {
    constructor() {
        // 标注类型
        this.tagTypeArr = ['3-gram', '5-gram', '8-gram']
        // 标注类型
        this.tagType = 1;
        //标注标签
        this.Lables = [
            {
                name: '概念',
                lable: 'IM',
                color: '#1f77b4'
            }, {
                name: '容器',
                lable: 'IMC',
                color: '#8B6969'
            }, {
                name: '构件',
                lable: 'IMM',
                color: '#9467bd'
            }, {
                name: '企业',
                lable: 'ORG',
                color: '#17becf'
            }, {
                name: '产品',
                lable: 'PRO',
                color: '#2ca02c'
            }, {
                name: '难题',
                lable: 'EPRO',
                color: '#d62728'
            }, {
                name: '活动',
                lable: 'ACT',
                color: '#bcbd22'
            }, {
                name: '需求',
                lable: 'DMD',
                color: '#ff7f0e'
            },
        ]

        const tag = this;
        //初始化
        this.initTag();
          this.textFont = {
            fontSize: 20,
            fontColor: 'black'
        }
        this.lableMap = new Map();
        this.Lables.forEach(l => this.lableMap.set(l.lable, l))
        this.width = $('svg').parent().width();
        this.height = 600;
        this.svg = d3.select('#svg').attr('width', this.width)
            .attr('height', this.height)
        this.textArea = this.svg.append("g").attr("class", "block");
        this.initPriMenu()
        // this.initSubMenu()

    }
    initTag() {
        this.Lables.forEach(l => {
            $("#tag-ul")
                .append(
                    $(`<li class="list-group-item d-flex justify-content-between lh-condensed" >`)
                        .append(`<div><h6 class="my-0">${l.name}</h6></div>`)
                        .append(`<span class="text-muted" ">${l.lable}</span>`)
                        .append(`<span class="text-muted" style="background-color:${l.color}">&nbsp;&nbsp;&nbsp;</span>`)

                )
        })
    }

    /**
     * 根据文字初始化对象
     * @param text
     */
    init(text) {
        this.textArr = text.split('\n').map(text => {
            let o = text.split('\t');
            let tc =['O',''];
            if(o.length==2){
                tc = o[1].split('-');
                if(tc.length==1){
                    tc.push('');
                }
            }
            return new Object({
                text: o[0].trim(),
                tag:tc[0].trim(),
                class: tc[1].trim(),
            })
        });
        this.refresh()
    }
    refresh() {
        //清空画布
        this.textArea.selectAll("g").remove();
        let arr = this.textArr;//文字对象
        let blockArr = [];
        this.blockArr = blockArr;
        let pre = 'O';
        let blockObj;
        this.textArr.forEach((v,i,a)=>{
            switch (pre) {
                case 'S':
                case 'E':
                case 'O':
                    if(v.tag=='B'||v.tag=='O'||v.tag=='S'){
                        blockObj = {
                            text: v.text,
                            class: v.class,
                            left:i,
                            right:i,
                        }
                        blockArr.push(blockObj);
                        pre=v.tag;
                    }else {
                        console.log(`发生标注错误 PRE:${pre},CUR:${v.tag}`)
                    }
                    break;
                case 'B':
                case 'M':
                    if(v.tag=='M'&& v.class==blockObj.class){
                        blockObj.text +=v.text;
                    }else if(v.tag=='E' && v.class==blockObj.class){
                        blockObj.text +=v.text;
                        blockObj.right =i;
                        pre='E';
                    }else {
                        console.log(`发生标注错误 PRE:${pre},CUR:${v.tag}`)
                    }
                    break;
                default:
                    break;
            }
        })
        for (let i = 0; i < blockArr.length; i++) {
            let x = 0, y = 0, color = 'gry', w = blockArr[i].text.length * this.textFont.fontSize, h = this.textFont.fontSize * 1.1;

            if (i > 0) {
                x = blockArr[i - 1].x + (blockArr[i - 1].text.length + 0.3) * this.textFont.fontSize;
                y = blockArr[i - 1].y;
            }
            const x2 = x + w;
            if (x2 >= this.width-100) {
                x = 0;
                y += (h * 5);
            }
            blockArr[i] = {
                x: x,
                y: y,
                left:blockArr[i].left,
                right:blockArr[i].right,
                height: h,
                width: w,
                index: i,
                text: blockArr[i].text,
                color: 'white',
                class: blockArr[i].class
            }
        }
        this.svg.attr('height', blockArr[blockArr.length-1].y+300)
        this.svg.attr('view', blockArr[blockArr.length-1].y+300)
        this.drawText(blockArr)

        let arr2 = blockArr.filter(e => e.class != undefined && e.class.length > 0).map(d => new Object({
            lx: d.x,
            ly: d.y + d.height,
            rx: d.x + d.width,
            ry: d.y + d.height,
            tx: d.x + d.width / 2,
            ty: d.y + d.height,
            bx: d.x + d.width / 2,
            by: d.y + d.height + 10,
            class: d.class
        }))
        this.drawTag(arr2)

    }

    drawText(arr) {
        const tag = this;
        const text = this.textArea.append("g").selectAll("text")
            .data(arr)
            .join("text")
            .attr('x', d => d.x)
            .attr('y', d => d.y)
            .attr("dominant-baseline", "hanging")
            .attr('font-size', this.textFont.fontSize)
            .text(d => d.text);
        this.block = this.textArea.append("g").selectAll('rect')
            .data(arr)
            .join("rect")
            .attr('x', d => d.x)
            .attr('y', d => d.y)
            .attr('height', d => d.height)
            .attr('width', d => d.width)
            .attr("fill", d => this.getColor(d.class))
            .attr("stroke", 'white')
            .attr("opacity", '0.2')
            .attr("index", d=>d.index)
            .attr("stroke-width", '2')
            .attr("class","block")
            // .on("mouseover", function (d) {
            //     d3.select(this).style("stroke-width", "5").attr("stroke", 'black');
            // })
            // .on("mouseout", function (d) {
            //     d3.select(this).style("stroke-width", '1').attr("stroke", 'white');
            // })
            .on("dblclick",function (d) {
                // if(tag.select){
                //     tag.select = false;
                //     tag.endSelect(d);
                // }else {
                    tag.select = true;
                    tag.startSelect(d);
                    d3.select(this).attr("class","block hover")
                // }
            })
            .on("click", function (d) {
                if(tag.select){
                    tag.switchPriMenuState(d.index, d.x, d.y + d.height)
                }
            })


    }
    startSelect(d){
        const index  = d.index;
        const tag = this;
        tag.selectPos = index;
        tag.selectLeft = index;
        tag.selectRight = index;
        tag.block.on('mouseover',function (d) {
            d3.selectAll('.block.hover')
                .attr('class',"block")
            if(d.index>=tag.selectPos){
                tag.selectRight=d.index;
            }
            if(d.index<=tag.selectPos){
                tag.selectLeft=d.index;
            }
            for(let i =tag.selectLeft;i<=tag.selectRight;i++){
                d3.select(`.block[index="${i}"]`).attr("class","block hover")
            }
        })
    }


    drawTag(arr2) {
        const tag = this;
        const hline = this.textArea.append("g").selectAll('line')
            .data(arr2)
            .join("line")
            .attr('x1', d => d.lx)
            .attr('y1', d => d.ly)
            .attr('y2', d => d.ry)
            .attr('x2', d => d.rx)
            .attr("stroke", d => this.getColor(d.class))
            .attr("stroke-width", '2')
        const vline = this.textArea.append("g").selectAll('line')
            .data(arr2)
            .join("line")
            .attr('x1', d => d.tx)
            .attr('y1', d => d.ty)
            .attr('y2', d => d.by)
            .attr('x2', d => d.bx)
            .attr("stroke", d => this.getColor(d.class))
            .attr("stroke-width", '2')
        const lable = this.textArea.append("g").selectAll('text')
            .data(arr2)
            .join("text")
            .attr('x', d => d.bx)
            .attr('y', d => d.by)
            .attr("dominant-baseline", "hanging")
            .attr("text-anchor", "middle")
            .attr('font-size', this.textFont.fontSize/3*2)
            .attr("fill", d => this.getColor(d.class))
            .attr("stroke", d => this.getColor(d.class))
            .attr("stroke-width", '1')
            .text(d =>
                this.lableMap.get(d.class).name
            );
        const block = this.textArea.append("g").selectAll('rect')
            .data(arr2)
            .join("text")
            .attr('x', d => d.lx)
            .attr('y', d => d.ly)
            .attr('width', d => this.textFont.fontSize*  this.lableMap.get(d.class).name.length)
            .attr('height', d => d.by-d.ty+this.textFont.fontSize)
            .attr("fill", d => this.getColor(d.class))
            .attr("opacity", '0.1')
            .text(d =>
                this.lableMap.get(d.class).name
            ).on('click',function (d) {
                tag.path = true;
                d3.select(this).attr('class','block node');
                tag.startPath(d);
            })
    }
    startPath(d){
        tag.startPath(d);
    }


    initPriMenu() {
        const tag = this;
        const fontSize =14;
        const height = fontSize * 5 / 4;
        let y = 0;
        let heightSum = 0;
        let widthSum = 0;
        const arr = this.Lables.map(lable => {
            const width = (lable.name.length + 1) * fontSize;
            widthSum = Math.max(width)
            const btn = new Object({
                name: lable.name,
                lable: lable.lable,
                color: lable.color,
                width: width,
                height: height,
                btnX: 0,
                btnY: y,
                fontX: fontSize / 2,
                fontY: y + 1 / 8 * fontSize,
                menuOn: false
            })
            heightSum += height * 1.2;
            y = heightSum;
            return btn;
        })
        let clean = {
            name: "重置",
            color: "RED",
            width: ("重置".length+ 1) * fontSize,
            height: height,
            btnX: 0,
            btnY: y,
            fontX: fontSize / 2,
            fontY: y + 1 / 8 * fontSize,
            menuOn: false
        }
        let bg={
            x:0,
            y:0,
            w:widthSum,
            h:heightSum
        }
        this.priMenu = this.svg.append("g");
        this.menuBg = this.priMenu.append("g")
        this.menuText = this.priMenu.append("g")
        this.menuBtn = this.priMenu.append("g")
        this.cleanBtn =  this.priMenu.append("g")
        this.menuBg.selectAll('rect')
            .data([bg])
            .join("rect")
            .attr('x', d =>d.x)
            .attr('y', d => d.y)
            .attr('width', d => d.w*1.2)
            .attr('height', d => d.h*1.2)
            .attr("opacity", 0)
        this.menuText.selectAll('text')
            .data(arr)
            .join("text")
            .attr('x', d => d.fontX)
            .attr('y', d => d.fontY)
            .attr("dominant-baseline", "hanging")
            .attr('font-size', fontSize)
            .text(d => d.name)
            .attr("fill", "black")
        this.menuBtn.selectAll('rect')
            .data(arr)
            .join("rect")
            .attr('x', d => d.btnX)
            .attr('y', d => d.btnY)
            .attr('height', d => d.height)
            .attr('width', d => d.width)
            .attr("fill", d => d.color)
            .attr("stroke", "black")
            .attr("stroke-width", '1')
            .attr("opacity", 0.5)
            .on("mouseover", function (d) {
                d3.select(this).style("stroke-width", "2");
            })
            .on("mouseout", function (d) {
                d3.select(this).style("stroke-width", '1');
            })

            .on("click", function (d) {
                tag.select=false;
                let left = tag.blockArr[tag.selectLeft].left;
                let right = tag.blockArr[tag.selectRight].right;
                if(left==right){
                    tag.textArr[left].tag='O';
                    tag.textArr[left].class= d.lable
                }else {
                    for (let i = left; i <=right ; i++) {
                        if(i==left){
                            tag.textArr[i].tag='B';
                        }else if(i==right){
                            tag.textArr[i].tag='E';
                        }else {
                            tag.textArr[i].tag='M';
                        }
                        tag.textArr[i].class= d.lable

                    }
                }
                        tag.hidePriMenu()
                        tag.refresh()
                save2Server()
                // let string = tag.priMenu.attr("transform").replace("translate", "")
                // let xy = string.substring(1, string.length - 1).split(", ");
                // tag.showSubMenu(parseFloat(xy[0]) + d.btnX + d.width + 10, parseFloat(xy[1]) + d.btnY, d.lable)
            })
        this.cleanBtn.append("rect")
            .attr('x',clean.btnX)
            .attr('y', clean.btnY)
            .attr('height', clean.height)
            .attr('width', clean.width)
            .attr("fill", clean.color)
            .attr("stroke", "black")
            .attr("stroke-width", '1')
            .attr("opacity", 0.5)
            .on("mouseover", function (d) {
                d3.select(this).style("stroke-width", "2");
            })
            .on("mouseout", function (d) {
                d3.select(this).style("stroke-width", '1');
            })
            .on("click", function (d) {
                tag.select=false;
                let left = tag.blockArr[tag.selectLeft].left;
                let right = tag.blockArr[tag.selectRight].right;

                for (let i = left; i <=right ; i++) {
                    tag.textArr[i].tag='O'
                    tag.textArr[i].class=''
                }
                tag.hidePriMenu()
                tag.refresh()
                save2Server()
                // let string = tag.priMenu.attr("transform").replace("translate", "")
                // let xy = string.substring(1, string.length - 1).split(", ");
                // tag.showSubMenu(parseFloat(xy[0]) + d.btnX + d.width + 10, parseFloat(xy[1]) + d.btnY, d.lable)
            })
        this.priMenu.attr("fill","white")
        this.priMenu.attr("style", "display:none");
    }

    switchPriMenuState(index, x, y) {
        if (this.menuIndex == index) {
            this.hidePriMenu();
            this.menuIndex = undefined;

        } else {
            // this.hideSubMenu()
            this.showPriMenu(x, y)
            this.menuIndex = index;
        }

    }
    showPriMenu(x, y) {
        this.priMenu.attr("transform", `translate(${x}, ${y})`);
        this.priMenu.attr("style", "display:display");
    }
    hidePriMenu() {
        this.priMenu.attr("style", "display:none");
    }
    getColor(lable) {
        if (lable == '') return 'white'
        if(!this.lableMap.has(lable)){
            return "RED"
        }
        return this.lableMap.get(lable).color
    }
    saveToText(){
        return this.textArr.map(t=>{
            if(t.class==''){
                return `${t.text}\t${t.tag}`
            }
            return `${t.text}\t${t.tag}-${t.class}`

        }).join("\n")
    }
}

