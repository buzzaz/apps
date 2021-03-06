package apps

import grails.transaction.Transactional
import ij.ImagePlus

@Transactional
class AppsService {

	def imagingService
	
    def serviceMethod() {

    }
	
	//trackProductReferer(request.getHeader("REFERER"), products)
	def trackProductReferer(String referer, Products products, User user) {
		
		try {
		
			if (referer!=null && !referer.equals("") && products!=null) {
				def productRefererInstance = new ProductReferer()
				productRefererInstance.referer=referer
				productRefererInstance.createTime=new Date()
				productRefererInstance.products = products
				productRefererInstance.user = user
				if(!productRefererInstance.hasErrors() && productRefererInstance.save()) {
					System.out.println("productRefererInstance saved")
				} else {
					System.out.println("productRefererInstance not saved")
				}
						  
			}
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
	
	//trackBLogReferer(request.getHeader("REFERER"), blog)
	def trackBlogReferer(String referer, Blog blog, User user) {
		
		try {
		
			if (referer!=null && !referer.equals("") && blog!=null) {
				def blogRefererInstance = new BlogReferer()
				blogRefererInstance.referer=referer
				blogRefererInstance.createTime=new Date()
				blogRefererInstance.blog = blog
				blogRefererInstance.user = user
				if(!blogRefererInstance.hasErrors() && blogRefererInstance.save()) {
					System.out.println("blogRefererInstance saved")
				} else {
					System.out.println("blogRefererInstance not saved")
				}
						  
			}
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
	
	//trackProductReferer(request.getHeader("REFERER"), products)
	def trackAppReferer(String referer, App app) {
		
		try {
		
			if (referer!=null && !referer.equals("") && app!=null) {
				def appRefererInstance = new AppReferer()
				appRefererInstance.referer=referer
				appRefererInstance.createTime=new Date()
				appRefererInstance.app = app
				if(!appRefererInstance.hasErrors() && appRefererInstance.save()) {
					System.out.println("appRefererInstance saved")
				} else {
					System.out.println("appRefererInstance not saved")
				}
						  
			}
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
	
	def addView(App app, String ip) {
		def view = new View()
		view.status = 1
		view.app = app
		view.createTime = new Date()
		view.ip = ip
		
		view.save flush:true
	}
	
	def addProductView(Products products, String ip, User user) {
		def view = new ProductView()
		view.status = 1
		view.products = products
		view.createTime = new Date()
		view.ip = ip
		view.user = user
		
		view.save flush:true
	}
	
	def addBlogView(Blog blog, String ip, User user) {
		def view = new BlogView()
		view.blog = blog
		view.createTime = new Date()
		view.ip = ip
		view.user = user
		
		view.save flush:true
	}
	
	def uploadProductPhoto(File file, Products products) {
		log.info "inside uploadProductPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			products.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			products.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			products.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadProductPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadBlogPhoto(File file, Blog blog) {
		log.info "inside uploadBlogPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			blog.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			blog.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			blog.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadBlogPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadInsectPhoto(File file, Insect insect) {
		log.info "inside uploadInsectPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			insect.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			insect.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			insect.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadInsectPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}

	def uploadDiseasePhoto(File file, Disease disease) {
		log.info "inside uploadDiseasePhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			disease.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			disease.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			disease.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadDiseasePhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadCouponPhoto(File file, Coupon coupon) {
		log.info "inside uploadCouponPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			coupon.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			coupon.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			coupon.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadCouponPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadCategoryPhoto(File file, Category category) {
		log.info "inside uploadCategoryPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			category.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			category.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			category.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadCategoryPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadBlogCategoryPhoto(File file, BlogCategory blogCategory) {
		log.info "inside uploadBlogCategoryPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			blogCategory.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			blogCategory.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			blogCategory.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadBlogCategoryPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadTreeWellnessPhoto(File file, TreeWellness treeWellness) {
		log.info "inside uploadTreeWellnessPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			treeWellness.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			treeWellness.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			treeWellness.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadTreeWellnessPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadPhotosPhoto(File file, Photos photos) {
		log.info "inside uploadPhotosPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			photos.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			photos.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			photos.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadTreeWellnessPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadVideosVideo(File file, Videos videos) {
		log.info "inside uploadVideosVideo"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		try {
			videos.imageOne = file.bytes
			
		} catch (Exception e) {
			
			log.info"uploadTreeWellnessPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadProjectsPhoto(File file, Projects projects) {
		log.info "inside uploadProjectsPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			projects.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			projects.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			projects.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadProjectsPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadTasksPhoto(File file, Tasks tasks) {
		log.info "inside uploadTasksPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			tasks.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			tasks.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			tasks.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadTasksPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadNotesPhoto(File file, Notes notes) {
		log.info "inside uploadNotesPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			notes.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			notes.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			notes.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadNotesPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadBookPhoto(File file, Book book) {
		log.info "inside uploadBookPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			book.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			book.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			book.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadChapterPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadChapterPhoto(File file, Chapter chapter) {
		log.info "inside uploadBookPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			chapter.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			chapter.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			chapter.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadChapterPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadPickupBookPhoto(File file, PickupBook pickupBook) {
		log.info "inside uploadPickupBookPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			pickupBook.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			pickupBook.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			pickupBook.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadPickupBookPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
	
	def uploadDonateBookPhoto(File file, DonateBook donateBook) {
		log.info "inside uploadDonateBookPhoto"
		
		String fileName = file.getAbsolutePath()
		
		log.info "fileName = " + fileName
		
		ImagePlus image = imagingService.openImagePlus(fileName);
		try {
			def output = imagingService.generate100x100(image, fileName, true);
			
			def files = new File(output)
			donateBook.imageOne = files.bytes
			
			output = imagingService.generate50x50(image, fileName, true);
			
			files = new File(output)
			donateBook.imageTwo = files.bytes
			
			output = imagingService.generate400x400(image, fileName);
			
			files = new File(output)
			donateBook.imageThree = files.bytes
			
		} catch (Exception e) {
			
			log.info"uploadDonateBookPhoto: error generating images"
			e.printStackTrace()
			
		}
		
	}
}
