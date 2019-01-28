import scrapy
from scrapy import cmdline

class spider_get(scrapy.Spider):
    name = 'bolsa'
    #start_urls = ['http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=MGLU3.SA',
    #              'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=BAHI3.SA',
    #              'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=BRSR3.SA'
    #]
    
    start_urls = [ 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=BBDC4.SA&page=1&size=80',    # Bradesco (3)
                    'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=BOEI34.SA&page=1&size=80',   # BOEING
                    'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=PETR4.SA&page=1&size=80',    # Petrobras (4)
                    'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=CIEL3.SA&page=1&size=80',    # Cielo (9)
                    'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=SANB3.SA&page=1&size=80',    # Santander (6)
                    'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=MGLU3.SA&page=1&size=80',    # Magazine Luiza
                    'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=BAHI3.SA&page=1&size=80',    # Bahema
                    'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=BRSR3.SA&page=1&size=80',    # Banrisul
                    'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=VALE3.SA&page=1&size=80',    # Vale (5)
                    'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=ITUB3.SA&page=1&size=80',    # Itau Unibanco (2)
                    'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=ABEV3.SA&page=1&size=80',    # Ambev (1)
                    'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=BBAS3.SA&page=1&size=80',    # B. Brasil (7)
                    'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=VIVT3.SA&page=1&size=80'    # Telefonica (8)
    ]

    # start_urls = [ 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=BBDC4.SA',    # Bradesco (3)
    #                 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=BOEI34.SA',   # BOEING
    #                 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=PETR4.SA',    # Petrobras (4)
    #                 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=CIEL3.SA',    # Cielo (9)
    #                 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=SANB3.SA',    # Santander (6)
    #                 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=MGLU3.SA',    # Magazine Luiza
    #                 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=BAHI3.SA',    # Bahema
    #                 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=BRSR3.SA',    # Banrisul
    #                 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=VALE3.SA',    # Vale (5)
    #                 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=ITUB3.SA',    # Itau Unibanco (2)
    #                 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=ABEV3.SA',    # Ambev (1)
    #                 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=BBAS3.SA',    # B. Brasil (7)
    #                 'http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=VIVT3.SA'    # Telefonica (8)

    # ]
    def parse(self,response):
      
        titulo = response.xpath('//*[@id="infoTable"]/dl/dd[4]//text()').extract_first()
        
        rows = response.xpath('//*[@id="tblInterday"]')
        data = rows.xpath('/html/body/div[4]/div[1]/div/table/tbody/tr/td[1]//text()').extract()
        cotacao = rows.xpath('/html/body/div[4]/div[1]/div/table/tbody/tr/td[2]//text()').extract()
        minima  = rows.xpath('/html/body/div[4]/div[1]/div/table/tbody/tr/td[3]//text()').extract()
        maxima  = rows.xpath('/html/body/div[4]/div[1]/div/table/tbody/tr/td[4]//text()').extract()
        variacao= rows.xpath('/html/body/div[4]/div[1]/div/table/tbody/tr/td[5]//text()').extract()
        volume  = rows.xpath('/html/body/div[4]/div[1]/div/table/tbody/tr/td[6]//text()').extract()
        # cont = 0
        # for row in rows:
        #     cont = cont + 1
        # print(cont)
        #print
        i = 0
        while i < 80:
            pacote = {
                'titulo' : titulo,
                'data' : data[i],
                'cotacao' : cotacao[i],
                'minima' : minima[i],
                'maxima' : maxima[i],
                'variacao' : variacao[i],
                'volume' : volume[i]
            }
            i = i + 1
            print(pacote)
            yield pacote
# Para rodar e gerar o JSON  --> scrapy runspider bolsa.py -o resultado.json
# Para rodar sem JSON --> scrapy runspider bolsa.py