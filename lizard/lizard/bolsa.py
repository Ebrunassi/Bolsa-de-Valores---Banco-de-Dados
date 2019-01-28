import scrapy

class spider_get(scrapy.Spider):
    name = 'bolsa'
    start_urls = ['http://cotacoes.economia.uol.com.br/acao/cotacoes-historicas.html?codigo=BBDC4.SA']

    def parse(self,response):
        pass