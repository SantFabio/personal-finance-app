import fitz
import sys

def main():
    if len(sys.argv) < 2:
        print("Usage: python extract_pdf.py <pdf_path>")
        sys.exit(1)
    
    pdf_path = sys.argv[1]
    try:
        doc = fitz.open(pdf_path)
        text = ""
        for i, page in enumerate(doc):
            text += f"\n--- Page {i+1} ---\n"
            text += page.get_text()
        print(text)
    except Exception as e:
        print(f"Error reading PDF: {e}")

if __name__ == "__main__":
    main()
