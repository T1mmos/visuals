How to run the demo application:
	- Main Class: gent.timdemey.visuals.core.kernel.Start
	- Program Argument: cfg.meta.plugin=gent.timdemey.visuals.demo.DemoPlugin
	
This program has dependencies on:
	- MigLayout:       http://www.miglayout.com/
	- Web Look&Feel:   http://weblookandfeel.com/
	- Guava:           https://github.com/google/guava

I removed support for skins to make the thing compile. I did some quick&dirty changes to
combobox renders which is why, in the demo application, the language combobox is rendered
incorrectly.
