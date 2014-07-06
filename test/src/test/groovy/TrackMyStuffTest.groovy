//@Grab('net.sourceforge.htmlunit:htmlunit:2.15')
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.WebAssert
import groovy.util.GroovyTestCase

import org.junit.Test
import static org.junit.Assert.*

class TrackMyStuffTest {

    @Test public void testGui() {
	
		//println "BEGIN test"
	
		def webClient = new WebClient()

		// HtmlPage
		def page = webClient.getPage("http://localhost:5050")

		WebAssert.assertTitleEquals(page, "Track My Stuff")
		
		assert page.asText().contains("A demo CRUD application written using Groovy and Ratpack.")

		// ----------------------------------------------
		// Create Test Data
		// ----------------------------------------------

		// Get anchor to href for adding a new item
		// and click to get the new page
		def newPage = page.getAnchorByText("Add a New Item!").click()

		//HtmlForm 
		// We know there is only 1 form, so we can grab the 1st one
		def form = newPage.getForms().get(0)

		// Set the input fields with sample data
		form.getInputByName("name").setValueAttribute("The book of Bubba");
		form.getInputByName("item_type").setValueAttribute("PDF");
		form.getInputByName("item_location_1").setValueAttribute("Drive 2");
		form.getInputByName("item_location_2").setValueAttribute("Ebook directory 1");
		// The below is a textarea attribute
		//form.getInputByName("description").setValueAttribute("Just a fake book.");

		// Save the form
		// NOTE: The "Save" Button does not have a name, or ID
		// but since there is only 1 button on this page, we can grab the 1st one
		def savePage = newPage.getElementsByTagName("button").get(0).click()	

		// ----------------------------------------------
		// Verify Test Data is there
		// ----------------------------------------------

		// HtmlTable 
		def table = savePage.getElementsByTagName("table").item(0)


		// NOTE: the columns in the cell are zero-based
		// The ID is the cell at row 1, column 0
		def testDataId = table.getCellAt(1,0).asText()

		assert "The book of Bubba", table.getCellAt(1,1).asText()
		//println "If we got here data with ID: ${testDataId} has successfully been created"

		// TODO: validate other cell data

		// ----------------------------------------------
		// Update Test Data created above
		// ----------------------------------------------

		// TODO:
		
		// ----------------------------------------------
		// Verify Update to Test Data
		// ----------------------------------------------
		
		// TODO:

		// ----------------------------------------------
		// Delete Test Data we added
		// ----------------------------------------------
		// println "Now deleting data with ID: ${testDataId}"

		def anchorDelete = savePage.getAnchorByHref("/delete/${testDataId}")
		def deletePage = anchorDelete.click()

		def deleteForm = deletePage.getForms().get(0)
		def deleteSubmit = deleteForm.getInputByValue("Yes, Delete It!").click()

		// ----------------------------------------------
		// Verify Test Data is Gone
		// ----------------------------------------------

		table = deleteSubmit.getElementsByTagName("table").item(0)
		assert table.getCellAt(2,0) == null

		// println "If we got here data with ID: ${testDataId} has successfully been deleted"
		
		// ----------------------------------------------
		// DONE
		// ----------------------------------------------
		// println "Test Successfully Completed"
		webClient.closeAllWindows()
		
	
	} / END TEST
	
    

}
