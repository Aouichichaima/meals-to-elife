const isPrime = (num) => { // with a time complexity of O(sqrt(n))
    for(let i = 2; i <= Math.sqrt(num); i++)
        if(num % i === 0) return false;
    return num > 1;
}


module.exports = isPrime;