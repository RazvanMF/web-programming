let list = [];
export function textInputted() {
    let entryString = document.getElementById("inputarea").value;
    list = entryString.split(' ').filter((element) => element !== '');
    list = list.map((element) => Number(element)).sort(function(a, b) {return a - b});
    console.log(list);

    document.getElementById('tablecontainer').innerHTML = generateTable();
}

export function generateTable() {
    let table = '<table class="dynamictable">'; 
    for (let rows = 0; rows < (list.length / 5); rows += 1) {
        table += '<tr class="dynamicrow">';
        for (let col = 0; col < 5; col += 1) {
            let elementToAdd = list[rows * 5 + col];
            if (elementToAdd === undefined) {
                elementToAdd = "VANISHED";
                table += ('<td class="dynamiccellvanished">' + elementToAdd + '</td>')
            }
            else {
                table += ('<td class="dynamiccell">' + elementToAdd + '</td>')
            }
        }
        table += '</tr>';
    }
    table += '</table>';  
    return table; 
}