SELECT DISTINCT ip, COUNT(ip) as count 
        FROM parsedlog WHERE date BETWEEN '2017-01-01.00:00:00' and '2017-01-01.14:00:00' 
        AND ip = '192.168.234.82' GROUP BY ip