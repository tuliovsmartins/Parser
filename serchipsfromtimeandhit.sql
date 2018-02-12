SELECT DISTINCT ip, COUNT(ip) as count 
        FROM parsedlog WHERE date BETWEEN '2017-01-01.00:00:00' and '2017-01-01.14:00:00' 
        GROUP BY ip HAVING COUNT(ip) >= 100