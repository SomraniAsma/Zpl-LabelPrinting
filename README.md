[![](https://jitpack.io/v/SomraniAsma/Zpl-LabelPrinting.svg)](https://jitpack.io/#SomraniAsma/Zpl-LabelPrinting)


# Zpl-LabelPrinting




#### A very useful and painless library for your ZPL label Printing!!
#### Written in kotlin.
#### Include Internet & VPN check.
#### Automatically connect to any laser printer (supporting ZPL language).
.
.
.


## Installation

#### in your root build.gradle :

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

#### Add the dependency :

```
dependencies {
	        implementation 'com.github.SomraniAsma:Zpl-LabelPrinting:1.0.2'
	}
```

## Usage

```Kotlin
import com.somraniasma.zpllabelprint.LabelPrinting


 var print= LabelPrinting(
   this@MainActivity,  // Activity context
   false,              // VPN existance(true= exist / false= !exist)
   ipAdress,     // Printer IP Adress
   port,               // Printer port
   "zplScript"         // ZPL label script to print
 )
 print.execute("") }
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.


