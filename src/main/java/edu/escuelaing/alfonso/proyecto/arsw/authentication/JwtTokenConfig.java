package edu.escuelaing.alfonso.proyecto.arsw.authentication;

public class JwtTokenConfig {
public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpQIBAAKCAQEAsb06RiQjiDJtrout013N8vmic4IVsoAkxt/PEeb9XpQLg52B\r\n" + 
			"5GufnGZOwZqj3lyjT4duRleA27byWURSLMbm6Lw78lfqLxOQyCo7AwNQuF43X15V\r\n" + 
			"E3tNrNFljDmvPmfqyLkwbAJqGCjwV0Id1eMu+1oP19N877C26bH/Q6L/LxFCAJOm\r\n" + 
			"HRwpA3OnQQs4j5XO0AKLyNmMN9szQil5dO0G4axpz5tFFH4uceITJZPXg7hY6ScX\r\n" + 
			"6eqNJgTwVki9AAA21w60HwuE/knOk5HyQdQ6IIGwZHKrTZj6hioJWfH5xUwpisSr\r\n" + 
			"2yxERXGGkOzRD+y/Vg+JGaGDvsMYYeltvQd0WwIDAQABAoIBAFB8POA4SzLIiyxo\r\n" + 
			"Kf2106X8bxjZRlP/fSflf1I/BGsIpTIjaAd0yOE83tsC2MO/tWA1FrWZuEtGS/Yq\r\n" + 
			"Xv/RzgyD+K1kLDLHLF/H59MUdEAYwgBYqjPZOTJR3Cckk/uxePsOCSRP75UjLLqG\r\n" + 
			"wRXy+3psrRHxvfiYUCwBI3OQYvf/mmPGI8BH/jfTdI7IQI3GtyBGayqw//ZcnDwT\r\n" + 
			"pqzZ70zBF+nr1mVUzgYr4EWxJ3rKLCBh6VePPlNtmR2ypgC+Pd8VAcjedRwYsYfx\r\n" + 
			"NjofujyyGENi8cqHiDPpErXLxulPhBBKiqWHElcx15Cyg0UcDPOu48KZwiH8Cudi\r\n" + 
			"ffPlO1ECgYEA2jKKsves3hSqlEI4d7YB3ROf4tXC0MiXxjylQ4loQiadp5TVEbaC\r\n" + 
			"Vl9Cv/qFzFGceXWt/nyovbfUIl1hiK24F3uvdZR+qAbeRu4oy9FN1eibfF1qcV+k\r\n" + 
			"c1BvAwwmysJj6GKmouGb4sCTmdxh6TQUsx8Yo8E4JJ85fPCUyuAVfUkCgYEA0IhJ\r\n" + 
			"mUQhBdagUhgVGmyZxsTRLwsENvV01OarjFvg3E8N2xcW+Uba7GEJFBAEQpf+/AH1\r\n" + 
			"3wX2OESANne136kRYka40evKmeJh5cl/E5agmEPnb+gNunBNlC4NBR+LXGA2mDJi\r\n" + 
			"y4wKGQ56rqKQndT3UTNnNsOP2MWvd/WcMAt9mIMCgYEA1z9iuhhAjThf6i/FWrrO\r\n" + 
			"7dNtN61eKO6pYuLyuUQ0+IT1o5rb8kRUIXaJ967hDOU5ckxX36QEJlvRRjFOhTGy\r\n" + 
			"WGAof9qEc6eK4PgSgLYyjaGQuUfCOKN8LFGdoaj8H6tSOa4P6sCbMj59nRvd7tr0\r\n" + 
			"zJsRiIGUq/PzF0CR1+zk5hkCgYEAtZJ1wyY3OsYuwkyF04RMQW6OVc/Z06DWIarY\r\n" + 
			"JFa77BhJkEMClfrEFJ5JMVbg3rtKIlPyqkaepU+/8604h47GvDOoKeCNgIBFjbdt\r\n" + 
			"FrlJDEvT8PS1P2kZo7SVUaitx7le0BbPPzyobjjVScny8frFfcasUFrnnXBD4MfT\r\n" + 
			"P2JB8dECgYEAxPtiIynmS2jCMIeszcHfqG6OH4HAbETMnyFDpMXQeHTDaH+FfKJk\r\n" + 
			"fYu04n74t4w5F9ZPffKX4Bl1jn0aakCpByzWUmNKmcHveP+sEpotezsSHPrduLcp\r\n" + 
			"3Nc1JB0p5kl6KChA1EHi5F63TQ8Hcky5Tn1Ryk5J9hGaHr26ljdyBqM=\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsb06RiQjiDJtrout013N\r\n" + 
			"8vmic4IVsoAkxt/PEeb9XpQLg52B5GufnGZOwZqj3lyjT4duRleA27byWURSLMbm\r\n" + 
			"6Lw78lfqLxOQyCo7AwNQuF43X15VE3tNrNFljDmvPmfqyLkwbAJqGCjwV0Id1eMu\r\n" + 
			"+1oP19N877C26bH/Q6L/LxFCAJOmHRwpA3OnQQs4j5XO0AKLyNmMN9szQil5dO0G\r\n" + 
			"4axpz5tFFH4uceITJZPXg7hY6ScX6eqNJgTwVki9AAA21w60HwuE/knOk5HyQdQ6\r\n" + 
			"IIGwZHKrTZj6hioJWfH5xUwpisSr2yxERXGGkOzRD+y/Vg+JGaGDvsMYYeltvQd0\r\n" + 
			"WwIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";

}
