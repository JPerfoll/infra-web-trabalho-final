package br.edu.unidavi.infrawebtrabalhofinal;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import br.edu.unidavi.infrawebtrabalhofinal.ItemRestController;

public class ItemResourceAssembler extends ResourceAssemblerSupport<Item, ItemResource> {

	public ItemResourceAssembler() {
		super(Item.class, ItemResource.class);
	}
	
	@Override
	public ItemResource toResource(Item item) {
		return new ItemResource(item, linkTo(methodOn(ItemRestController.class).get(item.getId())).withSelfRel());
	}
	
	@Override
	protected ItemResource instantiateResource(Item item) {
		return new ItemResource(item);
	}
}