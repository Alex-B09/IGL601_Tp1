const fs = require('fs');

loadFile = (fileName) => {
  return fs.readFileSync('../' + fileName).toString().split("\n");
};

conflict = (value) => {
  return typeof value === 'undefined' ? '*deleted*' : value;
};

merge = (o, a, b) => {
  let merged = [];
  let length = Math.max(a.length, b.length, o.length);

  for (let i = 0; i < length; i++) {
    if (a[i] === b[i]) {
      merged[i] = a[i];
    } else if (a[i] === o[i]) {
      merged[i] = b[i];
    } else if (b[i] === o[i]) {
      merged[i] = a[i];
    } else {
      // les trois fichiers sont differents, on affiche le conflit
      merged[i] = `>>>> conflit | original: ${conflict(o[i])}, a:${conflict(a[i])}, b:${conflict(b[i])} `  ;
    }
  }

  return merged
};

(() => {
  let a = loadFile("CompareA");
  let b = loadFile("CompareB");
  let original = loadFile("CompareOriginal");

  let merged = merge(original, a, b);

  let outputFile = 'CompareSortie';
  fs.writeFileSync(outputFile, merged.join('\n'));
})();