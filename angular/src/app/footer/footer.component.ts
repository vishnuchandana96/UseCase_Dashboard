import { Component, OnInit } from '@angular/core';
import { MatIconRegistry } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';
import { faFacebookSquare,faInstagram,faPinterestSquare,faLinkedin,faTwitterSquare} from '@fortawesome/free-brands-svg-icons';


@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

   ngOnInit() {
  }
  
  faFacebook = faFacebookSquare
  faInstagram=faInstagram
  faPinterest=faPinterestSquare
  faLinkedin=faLinkedin
  faTwitterSquare=faTwitterSquare
  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    iconRegistry.addSvgIcon(
      'facebook',
      sanitizer.bypassSecurityTrustResourceUrl('assets/images/facebook.svg')
    
      );
      iconRegistry.addSvgIcon(
      'twittor',
      sanitizer.bypassSecurityTrustResourceUrl('assets/images/iconmonstr-twitter-1.svg')
      );
      iconRegistry.addSvgIcon(
        'instagram',
        sanitizer.bypassSecurityTrustResourceUrl('assets/images/iconmonstr-instagram-12.svg')
        );
        iconRegistry.addSvgIcon(
          'copyright',
          sanitizer.bypassSecurityTrustResourceUrl('assets/images/copyright-symbol.svg')
          );
   }
}
