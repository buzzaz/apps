package apps

class ProductReferer {

    Date createTime
	String referer
    Products products
	
    static constraints = {
		createTime(nullable:false)
		referer(nullable:false, maxSize:1000)
		products(nullable:false)
                
    }

        static mapping = {
                version false
                //table 'app'
                //id generator:'sequence', params:[sequence:'app_seq']
        }
}
